package com.getir.readingisgoodapp.exception;

public class CustomerExistenceEception extends RuntimeException {
    public CustomerExistenceEception(String param){
        super(param);
    }
}
