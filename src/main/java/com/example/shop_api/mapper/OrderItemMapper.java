package com.example.shop_api.mapper;

import com.example.shop_api.JPA.entity.OrderItemEntity;
import com.example.shop_api.dto.OrderItemResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class OrderItemMapper {
    public OrderItemResponse toResponse(OrderItemEntity orderItem){
        BigDecimal amount = orderItem.getPriceAtPurchase().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        return new OrderItemResponse(
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getPriceAtPurchase(),
                amount
        );
    }
}
