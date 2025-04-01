package com.example.Chibi.controller;

import com.example.Chibi.dto.OrderDto;
import com.example.Chibi.dto.search.OrderSearchDto;
import com.example.Chibi.model.OrderModel;
import com.example.Chibi.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequestMapping("/pedido")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<OrderDto> getAll() {
        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping
    public OrderDto getById(@RequestParam String id) {
        return new OrderDto(orderService.findById(id));
    }

    @PostMapping("/search")
    public List<OrderDto> search(@RequestBody OrderSearchDto orderSearchDto) {
        List<Predicate<OrderModel>> predicates = orderSearchDto.breakdown();
        List<OrderModel> products = orderService.search(predicates);
        return products.stream().map(OrderDto::new).collect(Collectors.toList());
    }


    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto order) {
        try {
            OrderModel novoPedido = orderService.insert(order);
                return ResponseEntity.ok(new OrderDto(novoPedido));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
