package com.example.shop_api.config;

import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.exception.CustomerNotFoundException;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class SeederCustomer implements CommandLineRunner {
    private final CustomerRepository repository;

    public SeederCustomer(CustomerRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0){
            List<CustomerEntity> customer = List.of(
                    new CustomerEntity(null, "Nguyễn Văn An", "an@gmail.com", "0912345678",
                            LocalDate.of(2026, 1, 15)),
                    new CustomerEntity(null, "Trần Minh Đức", "duc@gmail.com", "0923456789",
                            LocalDate.of(2026, 3, 20)),
                    new CustomerEntity(null, "Lê Hoàng Nam", "nam@gmail.com", "0934567890",
                            LocalDate.of(2026, 5, 10)),
                    new CustomerEntity(null, "Phạm Thu Hà", "ha@gmail.com", "0945678901",
                            LocalDate.of(2026, 7, 25)),
                    new CustomerEntity(null, "Đỗ Quang Huy", "huy@gmail.com", "0956789012",
                            LocalDate.of(2026, 9, 5))
            );
            repository.saveAll(customer);
            System.out.println("Đã thêm khách hàng");
        }
//        System.out.println("Danh sách khách hàng");
//        repository.findAll().forEach(System.out::println);
//
//        System.out.println("Khách hàng có id = 2: ");
//        try {
//          CustomerEntity customerEntity =   repository.findById(2L)
//                    .orElseThrow(() ->
//                            new ProductNotFoundException("Không tìm thấy"));
//            System.out.println(customerEntity);
//        } catch (ProductNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Khách hàng id = 3 có tồn tại? "+ repository.existsById(3L));
//
//        System.out.println("Số lượng khách hàng: "+repository.count());
//
//        repository.deleteById(5L);
//        System.out.println("Xóa khách hàng thành công");



    }
}
