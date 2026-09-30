package com.example.shop_api.mapper;

import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.dto.OrderItemResponse;
import com.example.shop_api.dto.OrderResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderMapper {
    private final OrderItemMapper itemMapper;

    public OrderMapper(OrderItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
    public OrderResponse toResponse(OrderEntity order){
        List<OrderItemResponse> items = order.getOrderItemEntities()
                .stream()
                .map(item -> itemMapper.toResponse(item))
                .toList();
        BigDecimal totalAmount = items.stream()
                .map(item -> item.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderResponse(
                order.getId(),
                order.getCustomer().getFullName(),
                order.getOrderDate(),
                order.getOrderStatus(),
                items,
                totalAmount
        );
    }
}
