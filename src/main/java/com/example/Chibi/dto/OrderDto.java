package com.example.Chibi.dto;

import com.example.Chibi.model.client.Endereco;
import com.example.Chibi.model.client.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String nome;
    private String cpf;
    private Double total;
    private LocalDate data;
    private Endereco endereco;
    private List<ItemPedido> pedido;
}
