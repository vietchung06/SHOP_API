package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.JPA.entity.OrderEntity;
import com.example.shop_api.JPA.entity.OrderItemEntity;
import com.example.shop_api.JPA.entity.OrderStatus;
import com.example.shop_api.NotificationSender;
import com.example.shop_api.dto.*;
import com.example.shop_api.entity.Product;
import com.example.shop_api.exception.CustomerNotFoundException;
import com.example.shop_api.exception.InsufficientStockException;
import com.example.shop_api.exception.OrderNotFoundException;
import com.example.shop_api.exception.ProductNotFoundException;
import com.example.shop_api.mapper.OrderMapper;
import com.example.shop_api.repository.CustomerRepository;
import com.example.shop_api.repository.OrderRepository;
import com.example.shop_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository repository, CustomerRepository customerRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    public PageResponse<OrderResponse> getAll(Pageable pageable){
        Page<OrderEntity> page = repository.findAll(pageable);
        List<OrderResponse> content = page.getContent()
                .stream().map(orderMapper::toResponse).toList();

        return new PageResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );


    }

    //  //Tìm đơn hàng theo id khách hàng
    public List<OrderEntity> getByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }
    public OrderResponse getById(Long id) {

        OrderEntity order = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy order id: " + id
                        ));

        return orderMapper.toResponse(order);
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        System.out.println("customerId = " + request.customerId());

        for (OrderItemRequest item : request.items()) {
            System.out.println("productId = " + item.productId());
            System.out.println("quantity = " + item.quantity());
        }
        CustomerEntity customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Không tìm thấy khách hàng"));

        //tạo đơn hàng mới
        OrderEntity order = new OrderEntity();

        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.CANCELLED);
        order.setOrderItemEntities(new ArrayList<>());

        List<OrderItemEntity> items = new ArrayList<>();
        //xử lý từng sản phẩm
        for (OrderItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ProductNotFoundException("Không tìm thấy sản phẩm"));
            if (product.getQuantity() < itemRequest.quantity()) {
                throw new InsufficientStockException("Sản phẩm không đủ hàng");

            }
            //trừ tồn kho
            product.setQuantity(product.getQuantity() - itemRequest.quantity());
            productRepository.save(product);
            //giá hiện tại
            BigDecimal priceAtPurchase = product.getPrice();
            //tạo orderItem mới
            OrderItemEntity orderItem = new OrderItemEntity(null, order, product, itemRequest.quantity(), priceAtPurchase);
            //thêm vào
            items.add(orderItem);

        }
            //đưa danh sách orderItem vào order
            order.setOrderItemEntities(items);
        OrderEntity save = repository.save(order);
            return orderMapper.toResponse(save);

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
    public OrderResponse cancelOrder(Long orderId){
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
        return orderMapper.toResponse(order);

    }

    }


