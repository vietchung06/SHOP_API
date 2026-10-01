package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.dto.ProductRequest;
import com.example.shop_api.dto.ProductResponse;
import com.example.shop_api.entity.Product;
import com.example.shop_api.entity.Products;
import com.example.shop_api.exception.CategoryNotFoundException;
import com.example.shop_api.exception.DuplicateProductException;
import com.example.shop_api.exception.InvalidProductException;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.mapper.ProductMapper;
import com.example.shop_api.repository.CategorysRepository;
import com.example.shop_api.repository.ProductRepository;
import com.example.shop_api.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    private final ProductRepository repository;
    private final CategorysRepository categorysRepository;
    private final ProductMapper mapper;

    public ProductsService(ProductRepository repository, CategorysRepository categorysRepository, ProductMapper mapper) {
        this.repository = repository;
        this.categorysRepository = categorysRepository;
        this.mapper = mapper;
    }

    public List<ProductResponse> getAll(){
       List<Product> products = repository.findAll();
       List<ProductResponse> result = new ArrayList<>();
       for (Product product : products){
           result.add(mapper.toResponse(product));
       }
       return result;
    }

    public ProductResponse getById(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Không tìm thấy sản phẩm có id "+ id));
        return mapper.toResponse(product);
    }
    public ProductResponse create(ProductRequest request){
        if (repository.existsByName(request.name())){
            throw new DuplicateProductException("Sản phẩm đã tồn tại"+ request.name());
        }

        CategoryEntity category = categorysRepository.findById(request.categoryId())
                .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy danh mục id: "+ request.categoryId()));
        Product product = mapper.toEntity(request);
        product.setCategory(category);
        Product save = repository.save(product);
        return mapper.toResponse(save);
    }

    public ProductResponse update(Long id, ProductRequest request){
        Product oldProduct = repository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Không tìm thấy sản phẩm"));



        oldProduct.setName(request.name());
        oldProduct.setPrice(request.price());
        oldProduct.setQuantity(request.quantity());
        oldProduct.setBrand(request.brand());
        oldProduct.setDescription(request.description());
        oldProduct.setStatus(request.status());
        if (request.categoryId() != null){
            CategoryEntity category = categorysRepository.findById(request.categoryId())
                    .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy categoryId"));
            oldProduct.setCategory(category);
        }
        Product save = repository.save(oldProduct);
        return mapper.toResponse(save);
    }

    // chỉ sửa quantity
    public ProductResponse updateQuantity(Long id, Integer quantity){
       Product product =  repository.findById(id).orElseThrow(()-> new ProductNotFoundException("Không tìm thấy sản phẩm"));
       product.setQuantity(quantity);
       Product save = repository.save(product);
       return mapper.toResponse(save);
    }

    public void deleteById(Long id){
        getById(id);
        repository.deleteById(id);
    }

    public List<ProductResponse> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice){
        return repository.findByNameContainingIgnoreCaseAndPriceBetween(keyword, minPrice,maxPrice)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ProductResponse> searchByName(String keyword){
        if (keyword == null || keyword.isBlank()){
            throw new InvalidProductException("Từ khóa không được để trống");
        }
        return repository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    public List<ProductResponse> searchByPrice(BigDecimal minPrice, BigDecimal maxPrice){
        if (minPrice.signum() < 0 || maxPrice.signum() < 0){
            throw new InvalidProductException("Giá không được âm");
        }
        if (minPrice.compareTo(maxPrice) > 0){
            throw new InvalidProductException("Giá min không được lớn hơn max");
        }
        return repository.findByPriceBetween(minPrice,maxPrice)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ProductResponse> searchByQuantity(Integer threshold){
        if (threshold < 0){
            throw new InvalidProductException("Không được âm");
        }
        return repository.findByQuantityLessThan(threshold)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ProductResponse> getTopExpensive(Integer limit){
        if (limit <= 0 ){
            throw new InvalidProductException("Limit phải > 0");
        }
        return repository.findAllByOrderByPriceDesc(limit).stream()
                .limit(limit)
                .map(mapper::toResponse)
                .toList();
    }

    public boolean checkName(String name){
        if (name == null || name.isBlank()){
            throw new InvalidProductException("Tên không được để trống");
        }
        return repository.existsByName(name);
    }

    public List<ProductResponse> getByCategory(Long categoryId){
        return repository.findByCategoryId(categoryId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    //  Đếm sản phẩm theo categoryId
    public Long countByCategoryId(Long categoryId){
        return repository.countByCategoryId(categoryId);
    }
    public void test500() {
        int a = 10;
        int b = 0;

        int result = a / b;
    }
}
