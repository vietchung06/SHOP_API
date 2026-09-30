package com.example.shop_api.dto;

public record CategoryResponse(
        Long id,
        String fullName,
        String description,
        Long productCount
) {
}
