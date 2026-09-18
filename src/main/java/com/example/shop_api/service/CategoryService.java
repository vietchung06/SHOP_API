package com.example.shop_api.service;

import com.example.shop_api.entity.Category;
import com.example.shop_api.exception.CategoryNotFoundException;
import com.example.shop_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    public List<Category> getAll(){
        return repository.findAll();
    }
    public Category getById(int id){
        return repository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy id"));
    }
    public int create(Category category){
        if (category.getName() == null || category.getName().isBlank()){
            throw new IllegalArgumentException("Tên không được để trống");
        }
       return repository.save(category);


    }
    public Category update(int id, Category category){
        getById(id);
        if (category.getName() == null || category.getName().isBlank()){
            throw new IllegalArgumentException("Tên không được để trống");
        }
        repository.update(id,category);
        return category;
    }
    public void deleteById(int id){
        getById(id);
        repository.deleteById(id);
    }
}
