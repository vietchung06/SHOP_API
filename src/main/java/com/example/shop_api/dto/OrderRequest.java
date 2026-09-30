package com.example.shop_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotNull(message = " ID Danh mục không được để trống")
        Long customerId,
        @NotEmpty(message = "Danh sách dữ liệu không được để trống")
        @Valid
        List<OrderItemRequest> items
) {
}
