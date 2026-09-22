package com.example.shop_api.config;

import com.example.shop_api.JPA.entity.ProductStatus;
import com.example.shop_api.entity.Product;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final ProductRepository repository;

    public DataSeeder(ProductRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String...args){
        if(repository.count() == 0){
            List<Product> product = List.of(
                    // để id = null → database sẽ tự tạo ID Product.
                    new Product(null, "Áo thun", new BigDecimal("200000"), 10, 1L, "Áo thun nam", "Nike", ProductStatus.ACTIVE),
                    new Product(null, "Quần jean", new BigDecimal("350000"), 8, 1L, "Quần jean nam", "Levis", ProductStatus.ACTIVE),
                    new Product(null, "Áo khoác", new BigDecimal("500000"), 5, 1L, "Áo khoác nam", "Adidas", ProductStatus.ACTIVE),
                    new Product(null, "Áo sơ mi", new BigDecimal("300000"), 12, 1L, "Áo sơ mi nam", "Zara", ProductStatus.ACTIVE),
                    new Product(null, "Quần short", new BigDecimal("250000"), 15, 1L, "Quần short nam", "Nike", ProductStatus.ACTIVE),
                    new Product(null, "Giày thể thao", new BigDecimal("1200000"), 6, 2L, "Giày chạy bộ", "Adidas", ProductStatus.ACTIVE),
                    new Product(null, "Dép", new BigDecimal("180000"), 20, 2L, "Dép đi hằng ngày", "Nike", ProductStatus.ACTIVE),
                    new Product(null, "Mũ lưỡi trai", new BigDecimal("150000"), 9, 3L, "Mũ thời trang", "Adidas", ProductStatus.ACTIVE),
                    new Product(null, "Balo", new BigDecimal("450000"), 7, 3L, "Balo đi học", "Nike", ProductStatus.ACTIVE),
                    new Product(null, "Túi đeo chéo", new BigDecimal("320000"), 0, 3L, "Túi thời trang", "Zara", ProductStatus.DISCONTINUED)
            );
            repository.saveAll(product);
            System.out.println("Đã thêm thành công sp");


            }
//        Product product = new Product(null,"Áomlen", new BigDecimal(250000),6,2L,"Ấm áp", "Nike", ProductStatus.ACTIVE);
//        repository.save(product);

        Product product1 = repository.findById(11L)
                        .orElseThrow(()-> new ProductNotFoundException("Không tìm thấy sản phẩm"));
        product1.setName("Áo dài");
        repository.save(product1);

        System.out.println("Danh sách sản phẩm");
        repository.findAll().forEach(System.out::println);
        System.out.println("Sản phẩm có id =10");
        Product products = repository.findById(10L)
                .orElseThrow(()-> new ProductNotFoundException("Không tìm thấy sản phẩm"));
        System.out.println(products);
        System.out.println("kiểm tra id = 1 có tồn tại không: "+ repository.existsById(1L));
        System.out.println("Tổng số sản phẩm: "+ repository.count());
        repository.deleteById(1L);
        System.out.println("Đã xóa sản phẩm id = 1");
        }
    }

