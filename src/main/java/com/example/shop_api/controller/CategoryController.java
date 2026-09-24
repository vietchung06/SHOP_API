package com.example.shop_api.controller;

import com.example.shop_api.JPA.entity.CategoryEntity;
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
    public List<CategoryEntity> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public CategoryEntity getById(@PathVariable Long id){
        return categoryService.getById(id);
    }
    @PostMapping("/category")
    public CategoryEntity create(@RequestBody CategoryEntity categoryEntity){
        return categoryService.create(categoryEntity);
    }
    @PutMapping("/category/{id}")
    public CategoryEntity update(@PathVariable Long id, @RequestBody CategoryEntity categoryEntity){
        return categoryService.update(id, categoryEntity);
    }
    @DeleteMapping("/category/{id}")
    public String deleteById(@PathVariable Long id){
         categoryService.deleteById(id);
         return "Xóa thành công";
    }
    @GetMapping("/category/product-count")
    public List<String> getCategoryWithProductCount() {
        return categoryService.getCategoryWithProductCount();
    }
    // chuyển toàn bộ sản phẩm từ danh mục A sang B
    @PutMapping("category/{from}/move-products/{to}")
    public String moveProduct(@PathVariable Long from, @PathVariable Long to){
           categoryService.moveProduct(from,to);
           return "Chuyển sản phẩm thành công từ danh mục id: "+ from + " sang "+ to;
    }
}
