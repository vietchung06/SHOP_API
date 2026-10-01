package com.example.shop_api.exception;

public class CategoryNotFoundException extends ResourceNotFoundException{
    public CategoryNotFoundException(String message){
        super(message);
    }
}
