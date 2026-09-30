package com.example.shop_api.dto;

public record OrderItemRequest(
        Long productId,
        Integer quantity
) {
}
