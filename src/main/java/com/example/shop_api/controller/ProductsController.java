package com.example.shop_api.controller;

import com.example.shop_api.dto.ProductRequest;
import com.example.shop_api.dto.ProductResponse;
import com.example.shop_api.entity.Product;
import com.example.shop_api.entity.Products;
import com.example.shop_api.service.ProductsService;
import jakarta.validation.groups.Default;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController

public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAll(){
        List<ProductResponse> product = productsService.getAll();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getbyId(@PathVariable Long id){
        ProductResponse response = productsService.getbyId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request){
        ProductResponse createProduct =  productsService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,@RequestBody ProductRequest request){
        ProductResponse product1 =  productsService.update(id, request);
        return ResponseEntity.ok(product1);
    }
    //chỉ sửa tồn kho
    @PatchMapping("/products/{id}/quantity")
    public ResponseEntity<Product> updateQuantity(@PathVariable Long id, @RequestBody Integer quantity){
        Product product = productsService.updateQuantity(id, quantity);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id){
        productsService.deletebyId(id);
        return ResponseEntity.noContent().build();
    }

    // nhóm endpoint tìm kiếm
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> search(@RequestParam String keyword,
                                @RequestParam BigDecimal minPrice,
                                @RequestParam BigDecimal maxPrice){
        List<Product> products =  productsService.search(keyword, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/search-name")
    public ResponseEntity<List<Product>> searchByName(@RequestParam String keyword){
        List<Product> product = productsService.searchByName(keyword);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/products/price-range")
    public ResponseEntity<List<Product>> searchByPrice(@RequestParam BigDecimal minPrice,@RequestParam BigDecimal maxPrice){
        List<Product> products = productsService.searchByPrice(minPrice,maxPrice);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/low-stock")
    public ResponseEntity<List<Product>> searchByQuantity(@RequestParam(defaultValue = "10") Integer threshold){
        List<Product> products = productsService.searchByQuantity(threshold);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/top-price")
    public ResponseEntity<List<Product>> getTopExpensive(@RequestParam(defaultValue = "5") Integer limit){
        List<Product> products = productsService.getTopExpensive(limit);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/check-name")
    public ResponseEntity<Boolean> checkName(@RequestParam String name){
        boolean result = productsService.checkName(name);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable Long id){
        List<Product> products = productsService.getByCategory(id);
        return ResponseEntity.ok(products);
    }



}
