package com.example.demo.Services;


import com.example.demo.Infra.PessoaRepository;
import com.example.demo.Models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> pessoaRepo = pessoaRepository.findByEmail(email);
        if(pessoaRepo.isEmpty()){
            throw new UsernameNotFoundException("Could not find Pessoa with email: " + email);
        }
        Pessoa pessoa = pessoaRepo.get();
        return new org.springframework.security.core.userdetails.User(email, pessoa.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
