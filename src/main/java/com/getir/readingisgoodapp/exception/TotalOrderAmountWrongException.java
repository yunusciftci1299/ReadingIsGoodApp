package com.getir.readingisgoodapp.exception;

public class TotalOrderAmountWrongException extends RuntimeException {
    public TotalOrderAmountWrongException(String param){
        super(param);
    }
}