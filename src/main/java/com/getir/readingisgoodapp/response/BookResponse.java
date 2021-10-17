package com.getir.readingisgoodapp.response;

import com.getir.readingisgoodapp.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {
    private BookDTO bookDTO;
    public BookResponse(BookDTO dto){
        bookDTO=dto;
    }
}
