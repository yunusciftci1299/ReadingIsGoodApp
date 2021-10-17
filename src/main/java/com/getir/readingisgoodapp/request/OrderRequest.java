package com.getir.readingisgoodapp.request;

import com.getir.readingisgoodapp.dto.BookOrderDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private BookOrderDTO bookOrderDTO;
}
