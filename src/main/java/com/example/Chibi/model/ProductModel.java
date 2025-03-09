package com.example.Chibi.model;

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
@Document(collection = "produto")
public class ProductModel {

    @Id
    private ObjectId id;
    private String nome;
    private String descricao;
    private double preco;
    private String marca;

    @Field("url_imagem")
    private String urlImagem;
    private double desconto;
    private List<String> categoria;
}
