package com.example.shop_api.repository;

import com.example.shop_api.JPA.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    //Tìm đơn hàng theo id khách hàng
    List<OrderEntity> findByCustomerId(Long customerId);

    // trả đơn kèm danh sách item
    //"Lấy Order theo ID và lấy luôn OrderItem." Khi lấy Order, lấy luôn danh sách OrderItem trong cùng truy vấn.
    @Query("""
     SELECT o FROM OrderEntity o
          JOIN FETCH o.orderItemEntities
          WHERE o.id = :orderId
     """)
    Optional<OrderEntity> findByIdWithItems(@Param("orderId") Long orderId);

}
