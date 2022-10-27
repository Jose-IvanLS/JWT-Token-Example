package com.example.demo.Controllers;


import com.example.demo.Infra.PessoaRepository;
import com.example.demo.Models.Pessoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/user")
@RestController
@Slf4j
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @GetMapping("/teste")
    public ResponseEntity<String> SalvarPessoa(@RequestBody Pessoa jsonBody){
        log.info(jsonBody.getName() + " " + jsonBody.getEmail());

        //repository.save(jsonBody);

        return ResponseEntity.status(HttpStatus.OK).body("Sucesso ao salvar");
    }

    @GetMapping("/info")
    public Pessoa getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //log.info(repository.findByEmail(email).get().getName());
        return repository.findByEmail(email).get();
    }





}
