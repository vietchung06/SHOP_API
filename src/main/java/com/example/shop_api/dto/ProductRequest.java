package com.example.shop_api.dto;

import com.example.shop_api.JPA.entity.ProductStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Tên không được để trống")
        @Size(max = 100 ,message = "Tên sản phẩm không được quá 100 kí tự")
        String name,
        @NotNull(message = "Gía không được để trống")
        @Positive(message = "Giá phải lớn hơn 0")
        BigDecimal price,
        @NotNull(message = "Không được để trống quantity")
        @PositiveOrZero(message = "Số lượng không được âm")
        Integer quantity,
        @NotNull(message = "Không được để trống id danh mục")
        Long categoryId,

        String description,
        @NotNull(message = "Không được để trống brand")
        String brand,
        ProductStatus status
) {

}
