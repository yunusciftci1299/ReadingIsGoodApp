package com.getir.readingisgoodapp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateRequest {
    private String bookCode;
    private Integer availableStock;
}
