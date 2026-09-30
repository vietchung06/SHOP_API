package com.example.shop_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Không được để trống danh mục")
        @Size(min = 2, max = 50, message = "Tên phải từ 2 đến 50 kí tự")
        String fullName,
        String description
) {
}
