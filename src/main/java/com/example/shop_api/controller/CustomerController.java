package com.example.shop_api.controller;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.JPA.entity.CustomerEntity;
import com.example.shop_api.dto.CustomerRequest;
import com.example.shop_api.dto.CustomerResponse;
import com.example.shop_api.repository.CustomerRepository;
import com.example.shop_api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> getAll(){
        List<CustomerResponse> customer = customerService.getAll();
        return ResponseEntity.ok(customer);
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id){
        CustomerResponse customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }
    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request){
        CustomerResponse customer = customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @PutMapping("/customers/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody CustomerRequest request){
        return customerService.update(id, request);
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
         customerService.deleteById(id);
         return ResponseEntity.noContent().build();
    }
}
