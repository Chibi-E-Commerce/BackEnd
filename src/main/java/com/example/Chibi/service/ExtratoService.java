package com.example.Chibi.service;

import com.example.Chibi.model.Extrato;
import com.example.Chibi.model.ExtratoFileFormat;
import com.example.Chibi.model.OrderModel;
import com.example.Chibi.service.order.OrderService;
import org.springframework.stereotype.Service;

@Service
public class ExtratoService {

    private final OrderService orderService;

    public ExtratoService(OrderService orderService) {
        this.orderService = orderService;
    }

    public byte[] gerarExtrato(String pedidoId, ExtratoFileFormat formato) {
        OrderModel pedido = orderService.findById(pedidoId);

        Extrato e = new Extrato(pedido, formato);

        return e.gerar();
    }
}