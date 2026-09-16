package com.example.shop_api.service;

import com.example.shop_api.repository.ProductRepositoryDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProductService {
    private ProductRepositoryDemo repository; // 1 dependency + 2 Bean phù hợp → Spring không biết chọn → lỗi.

    public ProductService() {

    }

    public ProductService(ProductRepositoryDemo repository) {
        this.repository = repository;
    }


}

