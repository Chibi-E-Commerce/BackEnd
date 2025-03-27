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

    public byte[] gerarExtrato(String pedidoId, ExtratoFileFormat formato) throws Exception {
        OrderModel pedido = orderService.findById(pedidoId);

        Extrato e = new Extrato(pedido, formato);
        try {
            return e.gerar();
        } catch (Exception e1) {
            throw new Exception("Erro ao gerar extrato tipo " + formato + ": " + e1.getMessage());
        }
    }
}