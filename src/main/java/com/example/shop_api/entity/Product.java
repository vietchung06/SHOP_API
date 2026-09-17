package com.example.shop_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products") //→ Chỉ rõ Entity này tương ứng với bảng
public class Product {
    @Id                   // id là Primary Key
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Integer category_id;
    private String description;

}
