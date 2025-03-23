package com.example.Chibi.dto.client;

import com.example.Chibi.model.ClientModel;
import com.example.Chibi.model.client.Cartao;
import com.example.Chibi.model.client.Endereco;
import com.example.Chibi.model.client.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {

    private boolean adm;
    private ObjectId id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private List<Cartao> cartao;
    private List<ItemPedido> carrinho;

    public ClientResponse(ClientModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.adm = model.getAdm();
        this.cpf = model.getCpf();
        this.email = model.getEmail();
        this.dataNascimento = model.getDataNascimento();
        this.endereco = model.getEndereco();
        this.cartao = model.getCartao();
        this.carrinho = model.getCarrinho();
    }
}
