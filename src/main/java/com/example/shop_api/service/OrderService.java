package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.JPA.entity.OrderItemEntity;
import com.example.shop_api.JPA.entity.OrderStatus;
import com.example.shop_api.NotificationSender;
import com.example.shop_api.entity.Product;
import com.example.shop_api.exception.CustomerNotFoundException;
import com.example.shop_api.exception.InsufficientStockException;
import com.example.shop_api.exception.OrderNotFoundException;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.repository.CustomerRepository;
import com.example.shop_api.repository.OrderRepository;
import com.example.shop_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository repository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    //  //Tìm đơn hàng theo id khách hàng
    public List<OrderEntity> getByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Transactional
    public OrderEntity createOrder(Long customerId, Map<Long, Integer> productQuantities) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Không tìm thấy khách hàng"));

        //tạo đơn hàng mới
        OrderEntity order = new OrderEntity(null, customer, LocalDate.now(), OrderStatus.CANCELLED, new ArrayList<>());

        List<OrderItemEntity> items = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer productQuantity = entry.getValue();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Không tìm thấy sản phẩm"));
            if (product.getQuantity() < productQuantity) {
                throw new InsufficientStockException("Sản phẩm không đủ hàng");

            }
            //giá hiện tại
            BigDecimal priceAtPurchase = product.getPrice();
            //tạo orderItem mới
            OrderItemEntity orderItem = new OrderItemEntity(null, order, product, productQuantity, priceAtPurchase);
            //thêm vào
            items.add(orderItem);
            //trừ tồn kho
            product.setQuantity(product.getQuantity() - productQuantity);
            productRepository.save(product);
        }
            //đưa danh sách orderItem vào order
            order.setOrderItemEntities(items);
            return repository.save(order);

        }

        //trả đơn kèm danh sách item
    public OrderEntity getOrderDetail(Long orderId) {

        return repository.findByIdWithItems(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Không tìm thấy đơn hàng"));
    }

    //Không dùng JOIN FETCH
    @Transactional
    public OrderEntity getOrdersDetail(Long orderId) {

        OrderEntity order = repository.findById(orderId)
                .orElseThrow(() ->
                        new OrderNotFoundException("Không tìm thấy đơn hàng")
                );

        // Truy cập collection LAZY để Hibernate tải OrderItem
        order.getOrderItemEntities();

        return order;
    }

    //nghiệp vụ hủy đơn gồm đổi trạng thái đơn hàng và hoàn lại tồn kho
    @Transactional
    public OrderEntity cancelOrder(Long orderId){
        //lấy đơn hàng theo id
        OrderEntity order = repository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Khng tìm thấy đơn hàng"));
        //đổi sang trạng thái hủy
        order.setOrderStatus(OrderStatus.CANCELLED);
        int count = 0;
        //duyệt orderItem
        for (OrderItemEntity item : order.getOrderItemEntities()){
            //lấy ra product
            Product product = item.getProduct();
            //Cộng số lượng sp vào kho
            product.setQuantity(product.getQuantity() + item.getQuantity());
        }
        count++;

        // Giả lập lỗi sau khi đã xử lý sản phẩm đầu tiên
        if (count == 1) {
            throw new RuntimeException("Giả lập lỗi");
        }
        return order;

    }

    }


