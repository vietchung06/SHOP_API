package com.example.shop_api;

import com.example.shop_api.entity.Product;
import com.example.shop_api.repository.ProductRepositoryDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
@Primary
class EmailSender implements NotificationSender{

	@Override
	public void send(String message) {
		System.out.println("Gửi email: "+ message);
	}
}
@Component
class Sms implements NotificationSender{

	@Override
	public void send(String message) {
		System.out.println("Gửi Sms: "+ message);
	}
}
@Component
class PushSender implements NotificationSender{

	@Override
	public void send(String message) {
		System.out.println("Gửi push: "+ message);
	}
}



@SpringBootApplication
public class ShopApiApplication {

	public static void main(String[] args) {
//		Product product = Product.builder()
//				.id(1).name("Áo").price(10000).quantity(20)
//				.build(); // → lấy những dữ liệu đã chuẩn bị và tạo ra object Product thật.
//		System.out.println(product.getName());
		SpringApplication.run(ShopApiApplication.class, args);

		double a = 0.1;
		double b = 0.2;

		System.out.println("double: " + (a + b));

		BigDecimal x = new BigDecimal("0.1");
		BigDecimal y = new BigDecimal("0.2");

		System.out.println("BigDecimal: " + x.add(y));


	}

}
