package com.example.shop_api.config;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.JPA.entity.ProductStatus;
import com.example.shop_api.entity.Product;
import com.example.shop_api.exception.CategoryNotFoundException;
import com.example.shop_api.repository.CategorysRepository;
import com.example.shop_api.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class SeederCategory implements CommandLineRunner {
    private final CategorysRepository repository;
    private final ProductRepository productRepository;

    public SeederCategory(CategorysRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }



    @Override
    public void run(String... args) throws Exception {
//        if (repository.count() == 0) {
//            List<CategoryEntity>  category = List.of(
//                    new CategoryEntity(null, "Quần áo", "Các sản phẩm quần áo"),
//                    new CategoryEntity(null, "Giày dép", "Các sản phẩm giày và dép"),
//                    new CategoryEntity(null, "Phụ kiện", "Các sản phẩm phụ kiện"),
//                    new CategoryEntity(null, "Túi và balo", "Các loại túi và balo"),
//                    new CategoryEntity(null, "Đồ thể thao", "Các sản phẩm thể thao")
//            );
//            repository.saveAll(category);
//            System.out.println("Thêm danh mục thành công");
//        }
//        System.out.println("Danh sách danh mục");
//        repository.findAll().forEach(System.out::println);
//
//        System.out.println("Danh mục có id = 13");
//        CategoryEntity categoryEntity = repository.findById(13L).orElseThrow(()-> new CategoryNotFoundException("không tìm thấy danh mục"));
//        System.out.println(categoryEntity);
//
//        System.out.println("Danh mục id = 5 có tồn tại ? " + repository.existsById(5L));
//
//        System.out.println("Tổng số danh mục: "+ repository.count());
//
//         repository.deleteById(3L);
//        System.out.println("Xóa thành công");

//            CategoryEntity c1 = new CategoryEntity(null, "Dép mới", "Dép hot nhất",null);
//            CategoryEntity c2 = new CategoryEntity(null, "Áo mới", "Áo hot nhất",null);
//            CategoryEntity c3 = new CategoryEntity(null, "Giày mới", "Giày hot nhất",null);
//
//            repository.saveAll(List.of(c1,c2,c3));
//
//            Product p1 = new Product(null, "Dép nâu", new BigDecimal(100000),20, c1,"Dép nâu đẹp","LV", ProductStatus.ACTIVE);
//            Product p2 = new Product(null, "Áo đỏ", new BigDecimal(150000),1, c2,"Áo đỏ đẹp","Nike", ProductStatus.ACTIVE);
//            Product p3 = new Product(null, "Giày LV", new BigDecimal(200000),15, c3,"Giày LV đẹp","LV", ProductStatus.ACTIVE);
//
//            productRepository.saveAll(List.of(p1,p2,p3));


    }
}
