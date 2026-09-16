package com.example.shop_api.controller;

import com.example.shop_api.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final OrderService orderService;

    public TestController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/test")
    public String test(){
        orderService.createOrder();
        return "Đã kiểm tra";
    }
}
