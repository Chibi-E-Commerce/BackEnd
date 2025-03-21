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

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "cliente")
public class ClientModel {

    @Id
    private ObjectId id;
    @Field("adm")
    private Boolean adm;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private int idade;
    private Endereco endereco;
    private List<Cartao> cartao;
    private List<ItemPedido> carrinho;

}
