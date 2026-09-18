package com.example.shop_api.repository;

import com.example.shop_api.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
   int save(Category category);
   Optional<Category> findById(int id);
   List<Category> findAll();
   Category update(int id, Category category);
   boolean deleteById(int id);
}
