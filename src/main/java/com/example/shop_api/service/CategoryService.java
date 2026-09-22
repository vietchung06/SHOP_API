package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.entity.Category;
import com.example.shop_api.exception.CategoryNotFoundException;
import com.example.shop_api.exception.InvalidCategoryException;
import com.example.shop_api.exception.InvalidCustomerException;
import com.example.shop_api.repository.CategoryRepository;
import com.example.shop_api.repository.CategorysRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  private final CategorysRepository categorysRepository;

    public CategoryService(CategorysRepository categorysRepository) {
        this.categorysRepository = categorysRepository;
    }

    public List<CategoryEntity> getAll(){
        return categorysRepository.findAll();
    }
    public CategoryEntity getById(Long id){
      return categorysRepository.findById(id)
               .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy danh mục"));
    }
    public CategoryEntity create(CategoryEntity categoryEntity){
        if (categoryEntity.getFullName() == null || categoryEntity.getFullName().isBlank()){
            throw new InvalidCategoryException("Tên không được để trống");
        }
        return categorysRepository.save(categoryEntity);
    }

    public CategoryEntity update(Long id, CategoryEntity categoryEntity){
        if (categoryEntity == null){
            throw new InvalidCategoryException("Không được để trống thông tin khách hàng");
        }
        CategoryEntity oldCategory = getById(id);
        if (categoryEntity.getFullName() == null || categoryEntity.getFullName().isBlank()){
            throw new InvalidCategoryException("Tên không được để trống");
        }
        oldCategory.setFullName(categoryEntity.getFullName());
        oldCategory.setDescription(categoryEntity.getDescription());
        return categorysRepository.save(oldCategory);

    }
    public void deleteById(Long id){
        getById(id);
         categorysRepository.deleteById(id);
    }
}
