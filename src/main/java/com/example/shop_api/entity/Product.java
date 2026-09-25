package com.example.shop_api.entity;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.JPA.entity.OrderItemEntity;
import com.example.shop_api.JPA.entity.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)// khi nào cần dữ liệu thì mới lấy không cần sẽ không lấy
    @JoinColumn(name = "category_id") // chỉ định cột khóa ngoại
    private CategoryEntity category;

    private String description;

    private String brand;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItemEntity> orderItemEntityList;


}
