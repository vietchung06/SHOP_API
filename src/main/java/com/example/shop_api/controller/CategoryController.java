package com.example.shop_api.controller;

import com.example.shop_api.entity.Category;
import com.example.shop_api.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/category")
    public List<Category> findAll(){
        return categoryService.getAll();
    }
    @GetMapping("/category/{id}")
    public Category findById(@PathVariable int id){
        return categoryService.getById(id);
    }
    @PostMapping("/category")
    public int create(@RequestBody Category category){
        return categoryService.create(category);
    }
    @PutMapping("/category/{id}")
    public Category update(@PathVariable int id, @RequestBody Category category){
        return categoryService.update(id, category);
    }
    @DeleteMapping("/category/{id}")
    public String deleteById(@PathVariable int id){
        categoryService.deleteById(id);
        return "Xóa thư mục thành công";
    }
}
