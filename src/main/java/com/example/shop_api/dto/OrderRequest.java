package com.example.shop_api.dto;

import java.util.List;

public record OrderRequest(
        Long customerId,
        List<OrderItemRequest> items
) {
}
