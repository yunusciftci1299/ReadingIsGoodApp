package com.getir.readingisgoodapp.factory;

import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.domain.BookOrderDate;
import com.getir.readingisgoodapp.domain.BookOrderDetail;
import com.getir.readingisgoodapp.dto.BookOrderDTO;
import com.getir.readingisgoodapp.dto.BookOrderDetailDTO;
import com.getir.readingisgoodapp.dto.GroupYearAndMonthDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderFactory {

    public static BookOrder getBookOrder(){
        List<BookOrderDetail> bookOrderDetailList = List.of(
                new BookOrderDetail(1L,"SAF",4),
                new BookOrderDetail(1L,"NUT",4),
                new BookOrderDetail(1L,"ALI",4)
        );
        BookOrderDate bookOrderDate = new BookOrderDate(1L, LocalDate.parse("2020-10-10"),10,10,2020,20201010);
        return new BookOrder(1L,"4545",bookOrderDetailList, BigDecimal.valueOf(454),bookOrderDate);
    }

    public static Optional<BookOrder> getBookOrderByID(){
        List<BookOrderDetail> bookOrderDetailList = List.of(
                new BookOrderDetail(1L,"SAF",4),
                new BookOrderDetail(1L,"NUT",4),
                new BookOrderDetail(1L,"ALI",4)
        );
        BookOrderDate bookOrderDate = new BookOrderDate(1L, LocalDate.parse("2020-10-10"),10,10,2020,20201010);
        return Optional.of(new BookOrder(1L, "4545", bookOrderDetailList, BigDecimal.valueOf(454), bookOrderDate));
    }
    public static List<BookOrder> getOrdersByDates(){
        List<BookOrder> list= new ArrayList<>();
        List<BookOrderDetail> bookOrderDetailList = List.of(
                new BookOrderDetail(1L,"SAF",4),
                new BookOrderDetail(1L,"NUT",4),
                new BookOrderDetail(1L,"ALI",4)
        );
        BookOrderDate bookOrderDate = new BookOrderDate(1L, LocalDate.parse("2020-10-10"),10,10,2020,20201010);
         list.add(new BookOrder(1L,"4545",bookOrderDetailList, BigDecimal.valueOf(454),bookOrderDate));
         return list;
    }
    public static List<GroupYearAndMonthDTO> getGroupYearAndMonthDTOs(){
        return List.of(
                new GroupYearAndMonthDTO(2020,2),
                new GroupYearAndMonthDTO(2020,3),
                new GroupYearAndMonthDTO(2020,4)
        );
    }
}
