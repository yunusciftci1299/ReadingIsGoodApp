package com.getir.readingisgoodapp.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String param){
        super(param);
    }
}
