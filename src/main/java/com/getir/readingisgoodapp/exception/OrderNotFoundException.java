package com.getir.readingisgoodapp.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String param){
        super(param);
    }
}