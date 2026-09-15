package com.example.shop_api.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    int id;
    String name;
    double price;
    int quantity;
}
