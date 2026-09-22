package com.example.shop_api.exception;

public class InvalidCustomerException extends RuntimeException{
    public InvalidCustomerException(String message){
        super(message);
    }
}
