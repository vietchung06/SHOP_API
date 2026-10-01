package com.example.shop_api.exception;

public class OrderNotFoundException extends ResourceNotFoundException{
    public OrderNotFoundException(String message){
        super(message);
    }
}
