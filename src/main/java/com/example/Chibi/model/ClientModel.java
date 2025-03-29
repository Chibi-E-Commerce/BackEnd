package com.example.Chibi.model;


import com.example.Chibi.model.client.Cartao;
import com.example.Chibi.model.client.Endereco;
import com.example.Chibi.model.client.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "cliente")
public class ClientModel implements Comparable<ClientModel> {

    @Id
    private ObjectId id;
    @Field("adm")
    private Boolean adm;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    @Field("data_nascimento")
    private LocalDate dataNascimento;
    private Endereco endereco;
    private List<Cartao> cartao;
    private List<ItemPedido> carrinho;

    public ClientModel(OrderClientModel orderClientModel) {
        this.id = orderClientModel.getId();
        this.email = orderClientModel.getEmail();
        this.endereco = orderClientModel.getEndereco();
        this.nome = orderClientModel.getNome();
        this.cpf = orderClientModel.getCpf();
    }

    @Override
    public int compareTo(ClientModel o) {
        if (this.getNome() == null) {
            return 1;
        }else if (o.getNome() == null) {
            return -1;
        }
        return this.nome.compareTo(o.getNome());
    }
}
