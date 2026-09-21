package com.example.shop_api.JPA.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
    private Integer quantity;
    @Column(name = "price_at_purchase",precision = 10, scale = 2)
    private BigDecimal priceAtPurchase;
}
