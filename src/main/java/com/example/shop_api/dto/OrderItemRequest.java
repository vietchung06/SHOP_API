package com.example.shop_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotNull(message = "Id sản phẩm không được để trống")
        Long productId,
        @Positive(message = "Số lượng phải > 0")
        Integer quantity
) {
}
