package com.example.Chibi.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cartao {

    private String titular;
    private String numero;
    private String cvv;
    @Field("tipo_pagamento")
    private String tipoPagamento;
    private double saldo;
    private double limite;
    private Validade validade;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Validade {
        private int mes;
        private int ano;
    }
}
