package com.example.Chibi.dto;

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
}
