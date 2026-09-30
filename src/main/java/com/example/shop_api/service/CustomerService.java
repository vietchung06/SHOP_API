package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.dto.CustomerRequest;
import com.example.shop_api.dto.CustomerResponse;
import com.example.shop_api.exception.CustomerNotFoundException;
import com.example.shop_api.exception.DuplicateEmailException;
import com.example.shop_api.exception.InvalidCustomerException;
import com.example.shop_api.mapper.CustomerMapper;
import com.example.shop_api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
   private final CustomerRepository repository;
   private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public List<CustomerResponse> getAll(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();

    }

    public CustomerResponse getById(Long id){
        CustomerEntity customer = repository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Không tìm thấy khách hàng"));
        return mapper.toResponse(customer);
    }
    public CustomerResponse create(CustomerRequest request){
        if (request.fullName() == null || request.fullName().isBlank()){
            throw new InvalidCustomerException("Tên không được để trống");
        }
        if (request.email() == null || request.email().isBlank()){
            throw new InvalidCustomerException("Email không được để trống");
        }
        if (repository.existsByEmail(request.email())){
            throw new DuplicateEmailException("Email không được trùng");
        }
        CustomerEntity customer = mapper.toEntity(request);

        CustomerEntity saveCustomer = repository.save(customer);
        return mapper.toResponse(saveCustomer);
    }
    public CustomerResponse update(Long id, CustomerRequest request){

        CustomerEntity oldCustomer = repository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Không tìm thấy khách hàng id" + id));
        if (request.fullName() == null || request.fullName().isBlank()){
            throw new InvalidCustomerException("Tên không được để trống");
        }
        if (request.email() == null || request.email().isBlank()){
            throw new InvalidCustomerException("Email không được để trống");
        }
        oldCustomer.setFullName(request.fullName());
        oldCustomer.setEmail(request.email());
        oldCustomer.setPhone(request.phone());
        oldCustomer.setCreatedAt(request.createdAt());
        CustomerEntity save = repository.save(oldCustomer);
        return mapper.toResponse(save);
    }
    public void deleteById(Long id){
        getById(id);
        repository.deleteById(id);
    }
}
