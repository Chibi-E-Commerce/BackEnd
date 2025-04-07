package com.example.Chibi.service;

import com.example.Chibi.model.AlterarSenha;
import com.example.Chibi.repository.AlterarSenhaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AlterarSenhaService {

    private final AlterarSenhaRepository alterarSenhaRepository;

    public AlterarSenhaService(AlterarSenhaRepository alterarSenhaRepository) {
        this.alterarSenhaRepository = alterarSenhaRepository;
    }

    public void gerarCodigo(String email, String codigo) {
        alterarSenhaRepository.deleteByEmail(email);

        AlterarSenha token = new AlterarSenha();
        token.setEmail(email);
        token.setCodigo(codigo);
        token.setExpiracao(LocalDateTime.now().plusMinutes(30));

        alterarSenhaRepository.save(token);
    }

    public boolean validarCodigo(String email, String codigo) {
        if (email == null || codigo == null) {
            System.out.println("Email ou código nulo.");
            return false;
        }

        Optional<AlterarSenha> tokenOpt = alterarSenhaRepository.findByEmailAndCodigo(email.trim(), codigo.trim());

        if (tokenOpt.isPresent()) {
            return tokenOpt.get().getExpiracao().isAfter(LocalDateTime.now());
        } else {
            return false;
        }
    }


}