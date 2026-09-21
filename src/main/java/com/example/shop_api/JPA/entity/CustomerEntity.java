package com.example.shop_api.JPA.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true)
    private String email;
    @Column(length = 20)
    private String phone;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
