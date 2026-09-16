package com.example.shop_api.repository;

import com.example.shop_api.repository.ProductRepositoryDemo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary  // Thêm @Primary → nói cho Spring biết Bean nào được ưu tiên.
class MySqlProductRepository implements ProductRepositoryDemo {

    @Override
    public void save() {
        System.out.println("Lưu bằng My Sql");
    }
}