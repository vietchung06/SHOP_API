package com.example.shop_api.controller;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.dto.CategoryRequest;
import com.example.shop_api.dto.CategoryResponse;
import com.example.shop_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
   private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAll(){
        List<CategoryResponse> category =  categoryService.getAll();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id){
        CategoryResponse category =  categoryService.getById(id);
        return ResponseEntity.ok(category);
    }
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request){
        CategoryResponse category = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,@Valid @RequestBody CategoryRequest request){
        CategoryResponse category = categoryService.update(id, request);
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
         categoryService.deleteById(id);
         return ResponseEntity.noContent().build();
    }
    @GetMapping("/categories/product-count")
    public ResponseEntity<List<String>> getCategoryWithProductCount() {
        List<String> result =  categoryService.getCategoryWithProductCount();
        return ResponseEntity.ok(result);
    }
    // chuyển toàn bộ sản phẩm từ danh mục A sang B
    @PutMapping("/categories/{from}/move-products/{to}")
    public ResponseEntity<String> moveProduct(@PathVariable Long from, @PathVariable Long to){
           categoryService.moveProduct(from,to);
           return ResponseEntity.ok("Chuyển sản phẩm thành công từ danh mục id: "+ from + " sang "+ to);
    }
}
