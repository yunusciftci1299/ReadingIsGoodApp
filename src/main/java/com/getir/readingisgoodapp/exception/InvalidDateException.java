package com.getir.readingisgoodapp.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String param){
        super(param);
    }
}