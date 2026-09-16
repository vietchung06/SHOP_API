package com.example.shop_api.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    public double tinhGiamGia(double total, String memberRank){
        if (memberRank.equalsIgnoreCase("GOLD")){
            return total * 10 / 100;
        } else if (memberRank.equalsIgnoreCase("SILVER")) {
            return total * 5 /100 ;
        }else{
            return 0;
        }
    }

}
