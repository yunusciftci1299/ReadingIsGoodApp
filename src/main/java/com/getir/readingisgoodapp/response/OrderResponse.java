package com.getir.readingisgoodapp.response;

import com.getir.readingisgoodapp.dto.BookOrderDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private BookOrderDTO bookOrderDTO;
    public OrderResponse(BookOrderDTO dto){
        bookOrderDTO =dto;
    }
}
