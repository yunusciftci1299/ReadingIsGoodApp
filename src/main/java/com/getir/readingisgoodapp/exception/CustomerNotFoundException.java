package com.getir.readingisgoodapp.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String param){
        super(param);
    }
}
