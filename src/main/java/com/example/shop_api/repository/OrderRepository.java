package com.example.shop_api.repository;

import com.example.shop_api.JPA.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    //Tìm đơn hàng theo id khách hàng
    List<OrderEntity> findByCustomerId(Long customerId);

}
