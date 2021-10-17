package com.getir.readingisgoodapp.exception;

public class InvalidIDTypeException extends RuntimeException {
    public InvalidIDTypeException(String param){
        super(param);
    }
}