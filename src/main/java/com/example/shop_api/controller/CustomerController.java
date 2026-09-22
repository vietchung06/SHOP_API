package com.example.shop_api.controller;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.repository.CustomerRepository;
import com.example.shop_api.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customer")
    public List<CustomerEntity> getAll(){
        return customerService.getAll();
    }
    @GetMapping("/customer/{id}")
    public CustomerEntity getById(@PathVariable Long id){
        return customerService.getById(id);
    }
    @PostMapping("/customer")
    public CustomerEntity create(@RequestBody CustomerEntity customerEntity){
        return customerService.create(customerEntity);
    }
    @PutMapping("/customer/{id}")
    public CustomerEntity update(@PathVariable Long id, @RequestBody CustomerEntity customerEntity){
        return customerService.update(id, customerEntity);
    }
    @DeleteMapping("/customer/{id}")
    public String deleteById(@PathVariable Long id){
         customerService.getById(id);
         return "Xóa thành công";
    }
}
