package com.example.shop_api.controller;

import com.example.shop_api.config.AppConfig;
import com.example.shop_api.entity.AppInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {
    private final AppConfig appConfig;

    public AppInfoController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
    @GetMapping("/info")
    public AppInfo appInfo(){
        return appConfig.appInfo();
    }
}
