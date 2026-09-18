package com.example.shop_api.service;

import com.example.shop_api.entity.Products;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductRepository repository;

    public ProductsService(ProductRepository repository) {
        this.repository = repository;
    }
    public List<Products> getAll(){
        return repository.findAll();
    }
    public Products getbyId(int id){
        return repository.findbyId(id)
                .orElseThrow(() -> new ProductNotFoundException("Không tìm thấy sản phẩm có id "+ id));

    }
    public int create(Products products){
        if(products.getPrice() < 0){
            throw new IllegalArgumentException("Giá phải lớn hơn 0");
        }
        return repository.save(products);
    }
    public Products uapdate(int id, Products products){
        getbyId(id);
        if (products.getPrice() < 0){
            throw new IllegalArgumentException("Giá sản phẩm phải > 0");
        }
        repository.update(id, products);
        return products;
    }
    public void deletebyId(int id){
        getbyId(id);
        repository.deletebyId(id);
    }
}
