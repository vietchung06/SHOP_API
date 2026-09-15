package com.example.shop_api;

import com.example.shop_api.entity.Product;
import com.example.shop_api.repository.ProductRepositoryDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Primary  // Thêm @Primary → nói cho Spring biết Bean nào được ưu tiên.
class MySqlProductRepository implements ProductRepositoryDemo {

	@Override
	public void save() {
		System.out.println("Lưu bằng My Sql");
	}
}
@Repository
class MemoryProductRepository implements ProductRepositoryDemo {

	@Override
	public void save() {
		System.out.println("Lưu bằng Memory");
	}
}


@SpringBootApplication
public class ShopApiApplication {

	public static void main(String[] args) {
		Product product = Product.builder()
				.id(1).name("Áo").price(10000).quantity(20)
				.build(); // → lấy những dữ liệu đã chuẩn bị và tạo ra object Product thật.
		System.out.println(product.getId());
		System.out.println(product.getName());
		System.out.println(product.getPrice());
		System.out.println(product.getQuantity());
		SpringApplication.run(ShopApiApplication.class, args);

	}

}
