package com.example.shop_api.exception;

public class InvalidProductException extends RuntimeException  {
    public InvalidProductException(String message){
        super(message);
    }
}
