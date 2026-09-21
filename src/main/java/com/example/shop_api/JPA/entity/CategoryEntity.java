package com.example.shop_api.JPA.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name", unique = true,length = 255)
    private String fullName;
    private String description;
}
