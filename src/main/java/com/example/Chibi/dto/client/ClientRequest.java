package com.example.Chibi.dto.client;

import com.example.Chibi.model.client.Cartao;
import com.example.Chibi.model.client.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    private Endereco endereco;
    private List<Cartao> cartao;
    private LocalDate dataNascimento;
}
