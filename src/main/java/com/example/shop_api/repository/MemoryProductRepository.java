package com.example.shop_api.repository;

import com.example.shop_api.repository.ProductRepositoryDemo;
import org.springframework.stereotype.Repository;

@Repository
class MemoryProductRepository implements ProductRepositoryDemo {

    @Override
    public void save() {
        System.out.println("Lưu bằng Memory");
    }
}