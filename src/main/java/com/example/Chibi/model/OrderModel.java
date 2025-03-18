package com.example.Chibi.model;

import com.example.Chibi.model.client.Endereco;
import com.example.Chibi.model.client.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pedido")
public class OrderModel {
    @Id
    private ObjectId id;
    private String nome;
    private String cpf;
    private Double total;
    private LocalDate data;
    private Endereco endereco;
    private List<ItemPedido> produtos;
}
