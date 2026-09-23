package com.example.shop_api.config;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.exception.CategoryNotFoundException;
import com.example.shop_api.repository.CategorysRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SeederCategory implements CommandLineRunner {
    private final CategorysRepository repository;

    public SeederCategory(CategorysRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            List<CategoryEntity>  category = List.of(
                    new CategoryEntity(null, "Quần áo", "Các sản phẩm quần áo"),
                    new CategoryEntity(null, "Giày dép", "Các sản phẩm giày và dép"),
                    new CategoryEntity(null, "Phụ kiện", "Các sản phẩm phụ kiện"),
                    new CategoryEntity(null, "Túi và balo", "Các loại túi và balo"),
                    new CategoryEntity(null, "Đồ thể thao", "Các sản phẩm thể thao")
            );
            repository.saveAll(category);
            System.out.println("Thêm danh mục thành công");
        }
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

    }
}
