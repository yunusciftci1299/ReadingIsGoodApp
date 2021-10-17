package com.getir.readingisgoodapp.exception;

public class QuantityNegativeException extends RuntimeException {
    public QuantityNegativeException(String param){
        super(param);
    }
}