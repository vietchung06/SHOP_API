package com.example.shop_api.service;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private int count = 0;
    public int count(){
        count++;
        return count;
    }
}
