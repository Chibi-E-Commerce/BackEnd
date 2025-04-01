package com.example.Chibi.dto.product;

import com.example.Chibi.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String id;
    private String nome;
    private String descricao;
    private double preco;
    private String marca;
    private String urlImagem;
    private double desconto;
    private List<String> categoria;

    public ProductResponse(ProductModel model){
        this.id = model.getId().toString();
        this.nome = model.getNome();
        this.descricao = model.getDescricao();
        this.preco = model.getPreco();
        this.marca = model.getMarca();
        this.urlImagem = model.getUrlImagem();
        this.desconto = model.getDesconto();
        this.categoria = model.getCategoria();
    }
}