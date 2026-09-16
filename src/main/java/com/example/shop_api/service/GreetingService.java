package com.example.shop_api.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greet(String name){
        return "xin chào" + name;
    }
}