package com.getir.readingisgoodapp.response;

import com.getir.readingisgoodapp.dto.BookOrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListOrderResponse {
    private List<BookOrderDTO> bookOrderDTOList;

    public ListOrderResponse(List<BookOrderDTO> bookOrderDTOList) {
        this.bookOrderDTOList = bookOrderDTOList;
    }
}
