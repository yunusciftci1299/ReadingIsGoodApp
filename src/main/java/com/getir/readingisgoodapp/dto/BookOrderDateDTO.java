package com.getir.readingisgoodapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderDateDTO {
    private Long id;
    private LocalDate orderDate;
    private Integer orderDay;
    private Integer orderMonth;
    private Integer orderYear;
    private Integer orderDateIntValue;
}
