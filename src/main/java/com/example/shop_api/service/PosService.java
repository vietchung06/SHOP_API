package com.example.shop_api.service;

import org.springframework.stereotype.Service;

@Service
public class PosService {
private final DiscountService discountService;

    public PosService(DiscountService discountService) {
        this.discountService = discountService;
    }
    public String checkout(double total, String memberRank){
        double vat = total * 8 / 100;
        double phiTui = total < 100000 ? 1000 : 0;
        double giamGia = discountService.tinhGiamGia(total, memberRank);
        double tongTien = total + vat +phiTui -giamGia;
        return "Tiền hàng " + total
                + "\n Vat " + vat
                + "\n Phí túi " + phiTui
                + "\n Giảm giá " + giamGia
                + "\n Tổng tiền " + tongTien;
    }

}
