package com.example.shop_api.controller;

import com.example.shop_api.config.AppConfig;
import com.example.shop_api.entity.AppInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {
    private final AppConfig appConfig;

    public AppInfoController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
    @Value("${app.name}")
    private String appName;
    @Value("${app.support-email}")
    private String appSupportEmail;
    @GetMapping("/info")
    public String appInfo(){
        return "Tên app " + appName
                + "\n Email Support "+ appSupportEmail;
    }
}
