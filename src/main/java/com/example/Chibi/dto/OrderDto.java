package com.example.Chibi.dto;

import com.example.Chibi.model.OrderClientModel;
import com.example.Chibi.model.OrderModel;
import com.example.Chibi.model.client.ItemPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Double total;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private List<ItemPedido> itens;
    private OrderClientModel client;

    public OrderDto(OrderModel orderModel) {
        this.total = orderModel.getTotal();
        this.data = orderModel.getData();
        this.itens = orderModel.getItens();
        this.client = new OrderClientModel(orderModel.getClient());
    }
}
