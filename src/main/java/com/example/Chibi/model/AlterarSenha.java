package com.example.Chibi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alterar_senha")
public class AlterarSenha {
    @Id
    private String id;
    private String email;
    private String codigo;
    private LocalDateTime expiracao;
}