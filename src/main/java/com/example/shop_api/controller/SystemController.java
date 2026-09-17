package com.example.shop_api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SystemController {

    @Value("${spring.profiles.active}")
    private String nameProfile;
    @Value("${app.status}")
    private String status;
    @GetMapping("/health")
    public String health(){
        return "Tên Profile "+ nameProfile
                + "\n Trạng thái "+ status
                + "\n Giờ "+ LocalDateTime.now(); // thời gian hiện tại trên máy
    }
    // (/health) Mình phải tự viết @GetMapping("/health") và tự quyết định những thông tin muốn trả về
    // (/actuator/health) là API có sẵn của Spring Boot Actuator, Spring tự thực hiện health check và trả
    // về trạng thái của ứng dụng mà không cần mình tự viết Controller hay tự gán trạng thái UP.

    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;
    @Value("${app.support-email}")
    private String appSupportEmail;

    @GetMapping("/systeminfo")
    public String appInfo(){
        return "Tên app "+ appName
                + "\n Version "+ appVersion
                + "\n Support Email "+ appSupportEmail;
    }


}
