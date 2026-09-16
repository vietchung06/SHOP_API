package com.example.shop_api.controller;

import com.example.shop_api.service.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }
    @GetMapping("/count")
    public int count(){
        return counterService.count();
    }
}
