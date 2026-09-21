package com.example.shop_api.JPA.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class test {
    @Id
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
}
