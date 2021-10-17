package com.getir.readingisgoodapp.dto;

import com.getir.readingisgoodapp.domain.BookOrderDate;
import com.getir.readingisgoodapp.domain.BookOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderDTO {
    private Long id;
    private String customerNumber;
    private List<BookOrderDetailDTO> bookOrderDetails;
    private BigDecimal orderAmount;
    private BookOrderDate bookOrderDate;
}
