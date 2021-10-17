package com.getir.readingisgoodapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupYearAndMonthDTO {
    private Integer year;
    private Integer month;

    public GroupYearAndMonthDTO(Integer year, Integer month) {
        this.year = year;
        this.month = month;
    }
}
