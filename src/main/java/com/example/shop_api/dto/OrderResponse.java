package com.example.shop_api.dto;

import com.example.shop_api.JPA.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        Long orderId,
        String customerName,
        LocalDate orderDate,
        OrderStatus status,
        List<OrderItemResponse> items,
        BigDecimal totalAmount

) {
}
