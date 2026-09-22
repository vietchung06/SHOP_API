package com.example.shop_api.repository;

import com.example.shop_api.JPA.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorysRepository extends JpaRepository<CategoryEntity, Long> {
}
