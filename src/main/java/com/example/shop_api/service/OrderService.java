package com.example.shop_api.service;

import com.example.shop_api.NotificationSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final NotificationSender sender;

    public OrderService(@Qualifier("pushSender")NotificationSender sender) {
        this.sender = sender;
    }
    public void createOrder() {
        System.out.println("Tạo đơn hàng thành công");
        sender.send("Đơn hàng đã được tạo");
    }
}
