package com.example.shop_api.mapper;

import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.dto.CustomerRequest;
import com.example.shop_api.dto.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerEntity toEntity(CustomerRequest request){
        CustomerEntity customer = new CustomerEntity();
        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setCreatedAt(request.createdAt());
        return customer;
    }
    public CustomerResponse toResponse(CustomerEntity customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt()
        );
    }
}
