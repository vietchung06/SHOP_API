package com.example.shop_api.exception;

public class DuplicateCategoryException extends RuntimeException{
    public DuplicateCategoryException(String message){
        super(message);
    }
}