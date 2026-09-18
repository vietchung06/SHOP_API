package com.example.shop_api.repository;

import com.example.shop_api.entity.Product;
import com.example.shop_api.entity.Products;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    int save(Products products);
    Optional<Products> findbyId(int id);
    List<Products> findAll();

    void update(int id , Products products);
    boolean deletebyId(int id);
}
