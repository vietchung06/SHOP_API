package com.example.shop_api.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productName,
        Integer quantity,
        BigDecimal priceAtPurchase,
        BigDecimal amount

) {
}
