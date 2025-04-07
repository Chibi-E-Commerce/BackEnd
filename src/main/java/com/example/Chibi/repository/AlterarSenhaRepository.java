package com.example.Chibi.repository;

import com.example.Chibi.model.AlterarSenha;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlterarSenhaRepository extends MongoRepository<AlterarSenha, String> {
    Optional<AlterarSenha> findByEmailAndCodigo(String email, String codigo);
    void deleteByEmail(String email);
}
