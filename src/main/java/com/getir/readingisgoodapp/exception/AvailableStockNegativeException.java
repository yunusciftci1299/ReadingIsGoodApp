package com.getir.readingisgoodapp.exception;

public class AvailableStockNegativeException extends RuntimeException {
    public AvailableStockNegativeException(String param){
        super(param);
    }
}