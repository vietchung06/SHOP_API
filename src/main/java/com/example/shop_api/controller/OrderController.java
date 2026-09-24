package com.example.shop_api.controller;

import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/customer/{id}/order")
    public List<OrderEntity> getByCustomer(@PathVariable Long id){
        return orderService.getByCustomer(id);
    }
}
