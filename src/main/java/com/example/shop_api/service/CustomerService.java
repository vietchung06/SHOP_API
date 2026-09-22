package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.exception.CustomerNotFoundException;
import com.example.shop_api.exception.InvalidCustomerException;
import com.example.shop_api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
   private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    public List<CustomerEntity> getAll(){
        return repository.findAll();
    }

    public CustomerEntity getById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Không tìm thấy khách hàng"));
    }
    public CustomerEntity create(CustomerEntity customerEntity){
        if (customerEntity.getFullName() == null || customerEntity.getFullName().isBlank()){
            throw new InvalidCustomerException("Tên không được để trống");
        }
        if (customerEntity.getEmail() == null || customerEntity.getEmail().isBlank()){
            throw new InvalidCustomerException("Email không được để trống");
        }
        return repository.save(customerEntity);
    }
    public CustomerEntity update(Long id, CustomerEntity customerEntity){

        CustomerEntity oldCustomer = getById(id);
        if (customerEntity.getFullName() == null || customerEntity.getFullName().isBlank()){
            throw new InvalidCustomerException("Tên không được để trống");
        }
        if (customerEntity.getEmail() == null || customerEntity.getEmail().isBlank()){
            throw new InvalidCustomerException("Email không được để trống");
        }
        oldCustomer.setFullName(customerEntity.getFullName());
        oldCustomer.setEmail(customerEntity.getEmail());
        return repository.save(oldCustomer);
    }
    public void deleteById(Long id){
        getById(id);
        repository.deleteById(id);
    }
}
