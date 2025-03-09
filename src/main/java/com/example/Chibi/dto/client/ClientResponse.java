package com.example.Chibi.dto.client;

import com.example.Chibi.model.ClientModel;
import com.example.Chibi.model.client.Cartao;
import com.example.Chibi.model.client.Endereco;
import com.example.Chibi.model.client.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {

    private String nome;
    private String email;
    private int idade;
    private Endereco endereco;
    private List<Cartao> cartao;
    private List<Pedido> carrinho;


    public ClientResponse(ClientModel model) {
        this.nome = model.getNome();
        this.email = model.getEmail();
        this.idade = model.getIdade();
        this.endereco = model.getEndereco();
        this.cartao = model.getCartao();
    }
}
