package com.example.Chibi.service.order;


import com.example.Chibi.dto.search.ProductSearchDto;
import com.example.Chibi.model.OrderModel;
import com.example.Chibi.model.ProductModel;
import com.example.Chibi.service.product.ProductFilter;
import com.example.Chibi.service.util.FilterBuilder;
import org.bson.types.ObjectId;

import java.util.function.Predicate;

public class OrderFilter {
        /*
        * Id do cliente
        * Nome do cliente
        * Valor maior
        * Valor menor
        * Quantidade de itens
        * Contém produtos [0, 1, 2, 3, 4, 5]
        * */

    public static Predicate<OrderModel> porIdCliente(ObjectId clienteId) {
        return orderModel -> orderModel.getId().equals(clienteId);
    }

    public static Predicate<OrderModel> valorMinimo(double valor) {
        return orderModel -> orderModel.getTotal() >= valor;
    }

    public static Predicate<OrderModel> valorMaximo(double valor) {
        return orderModel -> orderModel.getTotal() <= valor;
    }

    public static Predicate<OrderModel> quantidadeItensMinima(int qnt) {
        return orderModel -> orderModel.getItens().size() >= qnt;
    }

    public static Predicate<OrderModel> quantidadeItensMaxima(int qnt) {
        return orderModel -> orderModel.getItens().size() <= qnt;
    }

    public static Predicate<OrderModel> porProduto(ProductSearchDto productSearch) {
        return orderModel -> ProductFilter.follows(orderModel.getProductList(), productSearch.breakdown());
    }
}
