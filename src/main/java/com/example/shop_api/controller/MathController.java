package com.example.shop_api.controller;

import com.example.shop_api.service.MathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }
    @GetMapping("/sum")
    public int sum(@RequestParam int a, @RequestParam int b){
        return mathService.sum(a,b);
    }
    @GetMapping("/multiply")
    public int multiply(@RequestParam int a, @RequestParam int b){
        return mathService.multiply(a,b);
    }
    @GetMapping("/prime")
    public String isPrime(@RequestParam int n){
        return mathService.isPrime(n);
    }
}
