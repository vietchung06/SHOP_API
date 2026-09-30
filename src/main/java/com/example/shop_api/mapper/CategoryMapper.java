package com.example.shop_api.mapper;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.dto.CategoryRequest;
import com.example.shop_api.dto.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryEntity toEntity(CategoryRequest request){
        CategoryEntity category = new CategoryEntity();
        category.setFullName(request.fullName());
        category.setDescription(request.description());
        return category;

    }
    public CategoryResponse toResponse(CategoryEntity categoryEntity, Long productCount){
        return new CategoryResponse(
                categoryEntity.getId(),
                categoryEntity.getFullName(),
                categoryEntity.getDescription(),
                productCount
        );
    }
}
