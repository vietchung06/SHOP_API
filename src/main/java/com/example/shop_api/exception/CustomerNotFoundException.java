package com.example.shop_api.exception;

public class CustomerNotFoundException extends ResourceNotFoundException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
