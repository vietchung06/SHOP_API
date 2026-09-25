package com.example.shop_api.controller;

import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //lấy đơn hàng của một khách
    @GetMapping("/customer/{id}/order")
    public List<OrderEntity> getByCustomer(@PathVariable Long id){
        return orderService.getByCustomer(id);
    }
    //Tạo đơn hàng mới
    @PostMapping("/order")
    public OrderEntity createOrder(@RequestParam Long customerId, @RequestBody Map<Long,Integer> productQuantities){

        return orderService.createOrder(customerId,productQuantities);
    }
    // trả đơn kèm danh sách item
    @GetMapping("/order/{id}")
    public OrderEntity getOrderDetail(@PathVariable Long id) {
        return orderService.getOrderDetail(id);
    }
    @GetMapping("/orders/{id}")
    public OrderEntity getOrdersDetail(@PathVariable Long id) {
        return orderService.getOrdersDetail(id);
    }
    @PutMapping("/order/{id}/cancel")
    public OrderEntity cancelOrder(@PathVariable Long id){
        return orderService.cancelOrder(id);
    }
}
