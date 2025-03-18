package com.example.Chibi.model;

import com.example.Chibi.model.client.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderClientModel {
    @Id
    private ObjectId id;
    private String nome;
    private String cpf;
    private String email;
    private Endereco endereco;

    public OrderClientModel(ClientModel clientModel) {
        this.nome = clientModel.getNome();
        this.cpf = clientModel.getCpf();
        this.email = clientModel.getEmail();
        this.endereco = clientModel.getEndereco();
    }
}
