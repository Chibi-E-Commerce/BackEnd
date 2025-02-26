package com.example.Chibi.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequest {

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private int idade;
}
