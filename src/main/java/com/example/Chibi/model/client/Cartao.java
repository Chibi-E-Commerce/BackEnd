package com.example.Chibi.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cartao {

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
