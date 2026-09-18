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
    @GetMapping("/products")
    public List<Products> getAll(){
        return productsService.getAll();
    }
    @GetMapping("/products/{id}")
    public Products getbyId(@PathVariable int id){
        return productsService.getbyId(id);
    }
    @PostMapping("/products")
    public int create(@RequestBody Products products){
        return productsService.create(products);
    }
    @PutMapping("/products/{id}")
    public Products update(@PathVariable int id,@RequestBody Products products){
        return productsService.uapdate(id, products);
    }
    @DeleteMapping("/products/{id}")
    public String deletebyId(@PathVariable int id){
        productsService.deletebyId(id);
        return "Đã xóa thành công";
    }
}
