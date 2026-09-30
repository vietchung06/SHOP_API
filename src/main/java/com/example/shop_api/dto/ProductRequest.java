package com.example.shop_api.dto;

import com.example.shop_api.JPA.entity.ProductStatus;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price,
        Integer quantity,
        Long categoryId,
        String description,
        String brand,
        ProductStatus status
) {

}
