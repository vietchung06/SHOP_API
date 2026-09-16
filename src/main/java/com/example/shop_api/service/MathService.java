package com.example.shop_api.service;

import org.springframework.stereotype.Service;

@Service
public class MathService {
    public int sum(int a, int b){
        return a + b;
    }
    public int multiply(int a, int b){
        return a * b;
    }
    public String isPrime(int n){
        int dem = 0;
        for (int i = 1; i <= n ; i++){
            if (n % i == 0){
                dem++;

            }

        }
        if (dem == 2){
            return n + " là số nguyên tố";
        }else {
            return n + " không phải số nguyên tố" ;
        }
    }
}
