package com.example.Chibi.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String nome;
    private String descricao;
    private double preco;
    private String marca;
    private String urlImagem;
    private double desconto;
    private List<String> categoria;

}
