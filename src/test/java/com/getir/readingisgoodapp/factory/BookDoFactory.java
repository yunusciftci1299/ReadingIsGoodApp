package com.getir.readingisgoodapp.factory;

import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.dto.BookDTO;

import java.math.BigDecimal;

public class BookDoFactory {

    public static Book getBook(){
        return new Book(Long.valueOf(1),"TESTBOOK","TESTBOOK", BigDecimal.valueOf(10),"TESTBOOK Romanı",12);
    }
    public static Book getBookForOrder(){
        return new Book(Long.valueOf(1),"TESTBOOK2","TESTBOOK2", BigDecimal.valueOf(10),"TESTBOOK2 Romanı",12);
    }
}
