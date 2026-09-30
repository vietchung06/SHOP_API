package com.example.shop_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CustomerRequest(
        String fullName,
        @Email(message = "Email không đúng định dạng")
        String email,
        @Pattern(regexp = "0\\d{9}", message = "Số điện thoại phải bắt đầu bằng 0 và phải có 10 số")
        String phone,
        LocalDate createdAt

) {
}
