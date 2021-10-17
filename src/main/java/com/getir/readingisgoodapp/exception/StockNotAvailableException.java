package com.getir.readingisgoodapp.exception;

public class StockNotAvailableException extends RuntimeException {
    public StockNotAvailableException(String param){
        super(param);
    }
}