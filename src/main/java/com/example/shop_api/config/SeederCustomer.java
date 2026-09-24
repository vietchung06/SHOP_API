package com.example.shop_api.config;

import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.JPA.entity.OrderStatus;
import com.example.shop_api.exception.CustomerNotFoundException;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.repository.CustomerRepository;
import com.example.shop_api.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class SeederCustomer implements CommandLineRunner {
    private final CustomerRepository repository;
    private final OrderRepository orderRepository;

    public SeederCustomer(CustomerRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }


    @Override
    public void run(String... args) throws Exception {
//        if (repository.count() == 0){
//            List<CustomerEntity> customer = List.of(
//                    new CustomerEntity(null, "Nguyễn Văn An", "an@gmail.com", "0912345678",
//                            LocalDate.of(2026, 1, 15)),
//                    new CustomerEntity(null, "Trần Minh Đức", "duc@gmail.com", "0923456789",
//                            LocalDate.of(2026, 3, 20)),
//                    new CustomerEntity(null, "Lê Hoàng Nam", "nam@gmail.com", "0934567890",
//                            LocalDate.of(2026, 5, 10)),
//                    new CustomerEntity(null, "Phạm Thu Hà", "ha@gmail.com", "0945678901",
//                            LocalDate.of(2026, 7, 25)),
//                    new CustomerEntity(null, "Đỗ Quang Huy", "huy@gmail.com", "0956789012",
//                            LocalDate.of(2026, 9, 5))
//            );
//            repository.saveAll(customer);
//            System.out.println("Đã thêm khách hàng");
//        }
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


        // Buổi 29 quan hệ
//        CustomerEntity c1 = new CustomerEntity(null, "Quốc", "quoc@gmail.com","0999888456",LocalDate.of(2026,8,12),null);
//        CustomerEntity c2 = new CustomerEntity(null, "Lam", "lam@gmail.com","0999888343",LocalDate.of(2026,4,23),null);
//        CustomerEntity c3 = new CustomerEntity(null, "Nghĩa", "nghia@gmail.com","0929888456",LocalDate.of(2026,2,2),null);
//
//        repository.saveAll(List.of(c1,c2,c3));
//
//        CustomerEntity c4 = repository.findById(4L).orElseThrow();
//        CustomerEntity c5 = repository.findById(1L).orElseThrow();
//
//        OrderEntity o1 = new OrderEntity(null, c1, LocalDate.of(2026,9,11), OrderStatus.CANCELLED);
//        OrderEntity o2 = new OrderEntity(null, c2, LocalDate.of(2026,5,15), OrderStatus.PENDING);
//        OrderEntity o3 = new OrderEntity(null, c3, LocalDate.of(2026,4,5), OrderStatus.COMPLETED);
//        OrderEntity o4 = new OrderEntity(null, c5, LocalDate.of(2026,7,8), OrderStatus.CANCELLED);
//        OrderEntity o5 = new OrderEntity(null, c4, LocalDate.of(2026,8,25), OrderStatus.CONFIRMED);
 //       OrderEntity o6 = new OrderEntity(null, c6, LocalDate.of(2026,5,5), OrderStatus.PENDING);
//
//        orderRepository.saveAll(List.of(o1,o2,o3,o4,o5,06));


    }
}
