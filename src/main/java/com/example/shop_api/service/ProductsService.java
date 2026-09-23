package com.example.shop_api.service;

import com.example.shop_api.entity.Product;
import com.example.shop_api.entity.Products;
import com.example.shop_api.exception.InvalidProductException;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.repository.ProductRepository;
import com.example.shop_api.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductsService {
    private final ProductRepository repository;

    public ProductsService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll(){
        return repository.findAll();
    }

    public Product getbyId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Không tìm thấy sản phẩm có id "+ id));

    }
    public Product create(Product product){
        if(product.getPrice().signum() < 0){
            throw new InvalidProductException("Giá phải lớn hơn 0");
        }
        if (product.getQuantity() < 0) {
            throw new InvalidProductException("Số lượng phải >= 0");
        }
            return repository.save(product);

    }

    public Product update(Long id, Product product){
        Product oldProduct = getbyId(id);

        if(product.getPrice().signum() < 0){
            throw new InvalidProductException("Giá phải lớn hơn 0");
        }
        if (product.getQuantity() < 0) {
            throw new InvalidProductException("Số lượng phải >= 0");
        }
       oldProduct.setCategoryId(product.getCategoryId());
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());

        return repository.save(oldProduct);
    }

    public void deletebyId(Long id){
        getbyId(id);
        repository.deleteById(id);
    }

    public List<Product> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice){
        return repository.findByNameContainingIgnoreCaseAndPriceBetween(keyword, minPrice,maxPrice);
    }

    public List<Product> searchByName(String keyword){
        if (keyword == null || keyword.isBlank()){
            throw new InvalidProductException("Từ khóa không được để trống");
        }
        return repository.findByNameContainingIgnoreCase(keyword);
    }
    public List<Product> searchByPrice(BigDecimal minPrice, BigDecimal maxPrice){
        if (minPrice.signum() < 0 || maxPrice.signum() < 0){
            throw new InvalidProductException("Giá không được âm");
        }
        if (minPrice.compareTo(maxPrice) > 0){
            throw new InvalidProductException("Giá min không được lớn hơn max");
        }
        return repository.findByPriceBetween(minPrice,maxPrice);
    }

    public List<Product> searchByQuantity(Integer threshold){
        if (threshold < 0){
            throw new InvalidProductException("Không được âm");
        }
        return repository.findByQuantityLessThan(threshold);
    }

    public List<Product> getTopExpensive(Integer limit){
        if (limit <= 0 ){
            throw new InvalidProductException("Limit phải > 0");
        }
        return repository.findAllByOrderByPriceDesc(limit).stream()
                .limit(limit)
                .toList();
    }

    public boolean checkName(String name){
        if (name == null || name.isBlank()){
            throw new InvalidProductException("Tên không được để trống");
        }
        return repository.existsByName(name);
    }
}
