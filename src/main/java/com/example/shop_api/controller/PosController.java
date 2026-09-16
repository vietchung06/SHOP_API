package com.example.shop_api.controller;

import com.example.shop_api.service.PosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PosController {
    private final PosService posService;

    public PosController(PosService posService) {
        this.posService = posService;
    }
    @GetMapping("/checkout")
    public String checkout(@RequestParam double total, @RequestParam String memberRank){
        return posService.checkout(total, memberRank);
    }
}
