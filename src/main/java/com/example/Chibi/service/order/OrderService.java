package com.example.Chibi.service.order;

import com.example.Chibi.dto.OrderDto;
import com.example.Chibi.model.ClientModel;
import com.example.Chibi.model.OrderModel;
import com.example.Chibi.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderModel insert(OrderDto order) {
        OrderModel orderModel = OrderModel.builder()
                .total(order.getTotal())
                .itens(order.getItens())
                .client(new ClientModel(order.getClient()))
                .data(order.getData())
                .build();
        return orderRepository.save(orderModel);
    }

    public OrderModel findById(String id) {
        return orderRepository.findById(new ObjectId(id)).orElse(null);
    }

    public List<OrderModel> search(List<Predicate<OrderModel>> filters) {
        List<OrderModel> orders = findAll();
        return OrderFilter.applyFilters(orders, filters);
    }


    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderModel> sortAll() {
        List<OrderModel> orders = findAll();
        Collections.sort(orders);
        return orders;
    }
}
