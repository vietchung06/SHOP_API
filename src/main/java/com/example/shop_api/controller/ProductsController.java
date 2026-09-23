package com.example.shop_api.controller;

import com.example.shop_api.entity.Product;
import com.example.shop_api.entity.Products;
import com.example.shop_api.service.ProductsService;
import jakarta.validation.groups.Default;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    // nhóm endpoint tìm kiếm
    @GetMapping("/product/search")
    public List<Product> search(@RequestParam String keyword,
                                @RequestParam BigDecimal minPrice,
                                @RequestParam BigDecimal maxPrice){
        return productsService.search(keyword, minPrice, maxPrice);
    }
    @GetMapping("/product/search-name")
    public List<Product> searchByName(@RequestParam String keyword){
        return productsService.searchByName(keyword);
    }
    @GetMapping("/product/price-range")
    public List<Product> searchByPrice(@RequestParam BigDecimal minPrice,@RequestParam BigDecimal maxPrice){
        return productsService.searchByPrice(minPrice,maxPrice);
    }
    @GetMapping("/product/low-stock")
    public List<Product> searchByQuantity(@RequestParam(defaultValue = "10") Integer threshold){
        return productsService.searchByQuantity(threshold);
    }
    @GetMapping("/product/top-price")
    public List<Product> getTopExpensive(@RequestParam(defaultValue = "5") Integer limit){
        return productsService.getTopExpensive(limit);
    }
    @GetMapping("/product/check-name")
    public boolean checkName(@RequestParam String name){
        return productsService.checkName(name);
    }

}
