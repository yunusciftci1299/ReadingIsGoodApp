package com.getir.readingisgoodapp.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler({BookNotFoundException.class})
    public String EntityNotFound(BookNotFoundException e){
        return "Book not found! :"+e.getMessage();
    }

    @ExceptionHandler({StockNotAvailableException.class})
    public String stockNotAvailable(StockNotAvailableException e){
        return "Stock not available for the request! :"+e.getMessage();
    }

    @ExceptionHandler({BookExistenceException.class})
    public String bookExistence(BookExistenceException e){
        return "The book already registered, you can update its stock! :"+e.getMessage();
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public String orderNotFound(OrderNotFoundException e){
        return "The Order not found!: "+e.getMessage();
    }

    @ExceptionHandler({CustomerExistenceEception.class})
    public String alreadyExistCustomer(CustomerExistenceEception e){
        return "The customer number already in used by another record! : "+e.getMessage();
    }

    @ExceptionHandler({AvailableStockNegativeException.class})
    public String availableStockNegative(AvailableStockNegativeException e){
        return "Available stock cannot be negative! : "+e.getMessage();
    }

    @ExceptionHandler({TotalOrderAmountWrongException.class})
    public String availableStockNegative(TotalOrderAmountWrongException e){
        return "The order amount is not right! : "+e.getMessage();
    }

    @ExceptionHandler({InvalidIDTypeException.class})
    public String invalidIDType(InvalidIDTypeException e){
        return "Invalid ID Type! : "+e.getMessage();
    }

    @ExceptionHandler({InvalidDateException.class})
    public String invalidDateType(InvalidDateException e){
        return "Invalid date type! the type must be in the format(yyyy-MM-dd): "+e.getMessage();
    }

    @ExceptionHandler({InvalidEntityBookOrderDateException.class})
    public String invalidBookOrderDate(InvalidEntityBookOrderDateException e){
        return "Invalid BookOrderDate entity!"+e.getMessage();
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public String customerNotFound(CustomerNotFoundException e){
        return "Customer not found! "+e.getMessage();
    }

    @ExceptionHandler({QuantityNegativeException.class})
    public String quantityNegative(QuantityNegativeException e){
        return "Quantity must no be negative! "+e.getMessage();
    }
}
