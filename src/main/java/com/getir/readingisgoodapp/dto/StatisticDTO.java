package com.getir.readingisgoodapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatisticDTO {
    private Integer totalOrderCount;
    private BigDecimal totalAmountPurchasedOrder;
    private Integer totalCountPurchasedBook;
    private String month;
    private String year;
}
