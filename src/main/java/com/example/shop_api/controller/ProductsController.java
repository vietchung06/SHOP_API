package com.example.shop_api.controller;

import com.example.shop_api.entity.Product;
import com.example.shop_api.entity.Products;
import com.example.shop_api.service.ProductsService;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }
    @GetMapping("/product")
    public List<Product> getAll(){
        return productsService.getAll();
    }

    @GetMapping("/product/{id}")
    public Product getbyId(@PathVariable Long id){
        return productsService.getbyId(id);
    }

    @PostMapping("/product")
    public Product create(@RequestBody Product product){
        return productsService.create(product);
    }

    @PutMapping("/product/{id}")
    public Product update(@PathVariable Long id,@RequestBody Product product){
        return productsService.update(id, product);
    }

    @DeleteMapping("/product/{id}")
    public String deletebyId(@PathVariable Long id){
        productsService.deletebyId(id);
        return "Đã xóa thành công";
    }
}
