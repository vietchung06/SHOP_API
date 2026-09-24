package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.NotificationSender;
import com.example.shop_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
//  //Tìm đơn hàng theo id khách hàng
  public List<OrderEntity> getByCustomer(Long customerId){
        return repository.findByCustomerId(customerId);
  }
}
