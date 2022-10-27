package com.example.demo.Infra;

import com.example.demo.Models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String email);
    //Pessoa findByEmail(String email);


}
