package com.getir.readingisgoodapp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationCustomerOrderRequest {
    private String customerNumber;
    private Integer pageSize;
    private Integer page;
}
