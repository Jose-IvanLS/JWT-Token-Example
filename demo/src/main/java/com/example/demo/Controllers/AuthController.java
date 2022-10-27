package com.example.demo.Controllers;


import com.example.demo.Infra.PessoaRepository;
import com.example.demo.Infra.security.JWTUtil;
import com.example.demo.Models.LoginModel;
import com.example.demo.Models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String,Object> registerHandler (@RequestBody Pessoa pessoa) {

        String encodedPass = passwordEncoder.encode(pessoa.getPassword());
        pessoa.setPassword(encodedPass);
        pessoaRepository.save(pessoa);
        String token = jwtUtil.generateToken(pessoa.getEmail());
        return Collections.singletonMap("jwt-token", token);
        //return  Collections.singletonMap("jwt-token", pessoa.getName());

    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginModel body){
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            authenticationManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getEmail());
            return Collections.singletonMap("jwt-token", token);
        } catch(AuthenticationException authEx){
            throw  new RuntimeException("Invalid Login Credentials");
        }
    }




}
