package com.getir.readingisgoodapp.factory;

import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.domain.BookOrderDate;
import com.getir.readingisgoodapp.domain.BookOrderDetail;
import com.getir.readingisgoodapp.domain.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerFactory {
    public static Customer getCustomer(){
        return new Customer(1L,"YUNUS","yunus@gmail.com","45545","12454");
    }

    public static List<BookOrder> getOrdersCustomer(){
        List<BookOrder> bookOrders= new ArrayList<>();
        List<BookOrderDetail> bookOrderDetailList = List.of(
                new BookOrderDetail(1L,"SAF",4),
                new BookOrderDetail(1L,"NUT",4),
                new BookOrderDetail(1L,"ALI",4)
        );
        BookOrderDate bookOrderDate = new BookOrderDate(1L, LocalDate.parse("2020-10-10"),10,10,2020,20201010);
        BookOrder bookOrder = new BookOrder(1L,"4545",bookOrderDetailList, BigDecimal.valueOf(454),bookOrderDate);
        bookOrders.add(bookOrder);
        return bookOrders;
    }
}
