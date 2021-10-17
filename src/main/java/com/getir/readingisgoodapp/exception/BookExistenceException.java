package com.getir.readingisgoodapp.exception;

public class BookExistenceException extends RuntimeException {
    public BookExistenceException(String param){
        super(param);
    }
}