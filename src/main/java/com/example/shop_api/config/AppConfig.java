package com.example.shop_api.config;

import com.example.shop_api.entity.AppInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
    @Bean
    public AppInfo appInfo(){
        return new AppInfo("AC","V1.4");
    }
}
