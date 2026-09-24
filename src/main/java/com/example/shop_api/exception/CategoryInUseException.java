package com.example.shop_api.exception;

public class CategoryInUseException extends RuntimeException{
    public CategoryInUseException(String message){
        super(message);
    }
}
