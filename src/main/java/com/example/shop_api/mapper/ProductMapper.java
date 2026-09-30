package com.example.shop_api.mapper;

import com.example.shop_api.dto.ProductRequest;
import com.example.shop_api.dto.ProductResponse;
import com.example.shop_api.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequest request){
        Product product =new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setStatus(request.status());
        return product;
    }
    public ProductResponse toResponse(Product product){
        Long categoryId = null;
        if (product.getCategory() != null){
            categoryId = product.getCategory().getId();
        }
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                categoryId,
                product.getDescription(),
                product.getBrand(),
                product.getStatus()
        );
    }
}
