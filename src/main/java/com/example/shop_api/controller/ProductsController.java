package com.example.shop_api.controller;

import com.example.shop_api.dto.PageResponse;
import com.example.shop_api.dto.ProductRequest;
import com.example.shop_api.dto.ProductResponse;
import com.example.shop_api.entity.Product;
import com.example.shop_api.entity.Products;
import com.example.shop_api.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

@RestController

public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Operation(summary = "Lấy danh sách sản phẩm có phân trang")// -- /v3/api-docs  Nó dùng để mô tả API cho tài liệu OpenAPI/Swagger.
    @GetMapping("/products")
    public ResponseEntity<PageResponse<ProductResponse>> getAll(Pageable pageable){
        PageResponse<ProductResponse> product = productsService.getAll(pageable);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Lấy danh sách sản phẩm theo id")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
        ProductResponse response = productsService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Thêm sản phẩm mới")
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request){
        ProductResponse createProduct =  productsService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
    }

    @Operation(summary = "Sửa sản phẩm theo id")
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,@Valid @RequestBody ProductRequest request){
        ProductResponse product1 =  productsService.update(id, request);
        return ResponseEntity.ok(product1);
    }
    //chỉ sửa tồn kho
    @Operation(summary = "sửa số lượng tồn kho theo id")
    @PatchMapping("/products/{id}/quantity")
    public ResponseEntity<ProductResponse> updateQuantity(@PathVariable Long id,@Valid @RequestBody ProductRequest request){
        ProductResponse product = productsService.updateQuantity(id, request.quantity());
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Xóa sản phẩm")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // nhóm endpoint tìm kiếm
    @Operation(summary = "Tìm kiếm sản phẩm theo từ khóa và khoảng giá")
    @GetMapping("/products/search")
    public ResponseEntity<PageResponse<ProductResponse>> search(@RequestParam String keyword,
                                @RequestParam BigDecimal minPrice,
                                @RequestParam BigDecimal maxPrice, Pageable pageable){
        PageResponse<ProductResponse> products =  productsService.search(keyword, minPrice, maxPrice,pageable);
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Tìm kiếm sản phẩm theo từ khóa")
    @GetMapping("/products/search-name")
    public ResponseEntity<List<ProductResponse>> searchByName(@RequestParam String keyword){
        List<ProductResponse> product = productsService.searchByName(keyword);
        return ResponseEntity.ok(product);
    }
    @Operation(summary = "Tìm kiếm sản phẩm theo khoảng giá")
    @GetMapping("/products/price-range")
    public ResponseEntity<List<ProductResponse>> searchByPrice(@RequestParam BigDecimal minPrice,@RequestParam BigDecimal maxPrice){
        List<ProductResponse> products = productsService.searchByPrice(minPrice,maxPrice);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/low-stock")
    public ResponseEntity<List<ProductResponse>> searchByQuantity(@RequestParam(defaultValue = "10") Integer threshold){
        List<ProductResponse> products = productsService.searchByQuantity(threshold);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/top-price")
    public ResponseEntity<List<ProductResponse>> getTopExpensive(@RequestParam(defaultValue = "5") Integer limit){
        List<ProductResponse> products = productsService.getTopExpensive(limit);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/check-name")
    public ResponseEntity<Boolean> checkName(@RequestParam String name){
        boolean result = productsService.checkName(name);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/categories/{id}/products")
    public ResponseEntity<PageResponse<ProductResponse>> getByCategory(@PathVariable Long id, Pageable pageable){
        PageResponse<ProductResponse> products = productsService.getByCategory(id,pageable);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/test-500")
    public String test500() {
        productsService.test500();
        return "OK";
    }


}
