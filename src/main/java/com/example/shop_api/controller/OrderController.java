package com.example.shop_api.controller;

import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.dto.OrderRequest;
import com.example.shop_api.dto.OrderResponse;
import com.example.shop_api.dto.PageResponse;
import com.example.shop_api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;



@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/orders")
    public ResponseEntity<PageResponse<OrderResponse>> getAll(Pageable pageable){
        PageResponse<OrderResponse> order = orderService.getAll(pageable);
        return ResponseEntity.ok(order);
    }
    //lấy đơn hàng của một khách
    @GetMapping("/customers/{id}/orders")
    public ResponseEntity<List<OrderEntity>> getByCustomer(@PathVariable Long id){
        List<OrderEntity> orders =  orderService.getByCustomer(id);
        return ResponseEntity.ok(orders);
    }
    //lấy đơn theo id
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id){
        OrderResponse response = orderService.getById(id);
        return ResponseEntity.ok(response);
    }
    //Tạo đơn hàng mới
    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){

        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // trả đơn kèm danh sách item
//    @GetMapping("/orders/{id}")
//    public ResponseEntity<OrderEntity> getOrderDetail(@PathVariable Long id) {
//        OrderEntity order = orderService.getOrderDetail(id);
//        return ResponseEntity.ok(order);
//    }

    @PutMapping("/orders/{id}/cancel")
    public OrderResponse cancelOrder(@PathVariable Long id){
        return orderService.cancelOrder(id);
    }
}
