package com.example.shop_api.exception;

public class DuplicateProductException extends RuntimeException{
    public DuplicateProductException(String message){
        super(message);
    }
}
