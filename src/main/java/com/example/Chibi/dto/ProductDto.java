package com.example.Chibi.dto;

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
public class ProductDto {

    private String nome;
    private String descricao;
    private double preco;
    private String marca;
    private String urlImagem;
    private double desconto;
    private List<String> categoria;

    public ProductDto(ProductModel model){
        this.nome = model.getNome();
        this.descricao = model.getDescricao();
        this.preco = model.getPreco();
        this.marca = model.getMarca();
        this.urlImagem = model.getUrlImagem();
        this.desconto = model.getDesconto();
        this.categoria = model.getCategoria();
    }
}
