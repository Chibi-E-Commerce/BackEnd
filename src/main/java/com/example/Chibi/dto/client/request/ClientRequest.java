package com.example.Chibi.dto.client.request;

import com.example.Chibi.model.client.Cartao;
import com.example.Chibi.model.client.Endereco;
import com.example.Chibi.model.client.ItemPedido;
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

    private Boolean adm;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private List<Cartao> cartao;
    private List<ItemPedido> carrinho;
}
