package com.example.shop_api.dto;

import java.time.LocalDate;

public record CustomerResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        LocalDate createdAt
) {
}
