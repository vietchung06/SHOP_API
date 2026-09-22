package com.example.shop_api.entity;

import com.example.shop_api.JPA.entity.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "products") //→ Chỉ rõ Entity này tương ứng với bảng
public class Product {
    @Id                   // id là Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 48, scale = 2)
    private BigDecimal price;

    private Integer quantity;

    @Column(name = "category_id")
    private Long categoryId;

    private String description;

    private String brand;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}
