package com.example.shop_api.repository;

import com.example.shop_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Tìm theo tên chứa keyword
    List<Product> findByNameContaining(String keyword);

    // 2. Tìm theo khoảng giá
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // 3. Sắp xếp tất cả sản phẩm theo giá giảm dần
    List<Product> findAllByOrderByPriceDesc();

    // 4. Tìm sản phẩm có quantity dưới ngưỡng, tìm hàng sắp hết
    List<Product> findByQuantityLessThan(Integer quantity);

    // 5. Tìm sản phẩm theo categoryId
    List<Product> findByCategoryId(Long categoryId);

    // 6. Đếm sản phẩm theo categoryId
    long countByCategoryId(Long categoryId);

    // 7. Kiểm tra tên sản phẩm có tồn tại không
    boolean existsByName(String name);

    // 8. Lấy 5 sản phẩm có giá cao nhất
    List<Product> findTop5ByOrderByPriceDesc();

    // tìm theo tên
    List<Product> findByName(String name);

    //name chứa keyword, không phân biệt hoa thường và price nằm trong khoảng minPrice → maxPrice
    List<Product> findByNameContainingIgnoreCaseAndPriceBetween(
            String keyword, BigDecimal minPrice, BigDecimal maxPrice);

    // theo từ khóa tên
    List<Product> findByNameContainingIgnoreCase(String keyword);

    // lấy top N giá cao nhất
    List<Product> findAllByOrderByPriceDesc(Integer limit);



    //  Viết bằng @Query JPQL
    // sản phẩm còn hàng trên mức giá X
    @Query("""
    SELECT p FROM Product p
        WHERE p.quantity > 0
        AND p.price > :price
    """)
    List<Product> findInStockAbovePrice(@Param("price") BigDecimal price);

    // ); tổng giá trị tồn kho
    @Query("""
    SELECT SUM (p.price * p.quantity) FROM Product p
    """)
    BigDecimal getTotleInventoryValue();

    // tìm theo tên danh mục
//    @Query("""
//    SELECT p FROM Product p
//        JOIN p.category c
//        ON c.id = p.categoryID
//        WHERE c.fullName = :name
//    """)
//    List<Product> findByNameCategoryId(@Param("name")String name);
}
