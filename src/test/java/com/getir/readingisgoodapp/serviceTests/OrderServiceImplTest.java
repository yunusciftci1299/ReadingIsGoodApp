package com.getir.readingisgoodapp.serviceTests;

import com.getir.readingisgoodapp.domain.*;
import com.getir.readingisgoodapp.dto.BookOrderDTO;
import com.getir.readingisgoodapp.dto.BookOrderDetailDTO;
import com.getir.readingisgoodapp.factory.BookDoFactory;
import com.getir.readingisgoodapp.factory.CustomerFactory;
import com.getir.readingisgoodapp.factory.OrderFactory;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.repository.CustomerRepository;
import com.getir.readingisgoodapp.repository.OrderRepository;
import com.getir.readingisgoodapp.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class OrderServiceImplTest {
    private OrderServiceImpl orderServiceImpl;
    private BookRepository bookRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerRepository=mock(CustomerRepository.class);
        orderRepository=mock(OrderRepository.class);
        bookRepository=mock(BookRepository.class);
        orderServiceImpl = new OrderServiceImpl(bookRepository,orderRepository,customerRepository);

        Book book = BookDoFactory.getBookForOrder();
        Mockito.when(bookRepository.findByCode(Mockito.anyString())).thenReturn(book);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        BookOrder bookOrder = OrderFactory.getBookOrder();
        Mockito.when(orderRepository.save(Mockito.any(BookOrder.class))).thenReturn(bookOrder);

        Customer customer = CustomerFactory.getCustomer();
        Mockito.when(customerRepository.findByCustomerNumber(Mockito.anyString())).thenReturn(customer);

        Mockito.when(bookRepository.findAvailableStockByCode(Mockito.anyString())).thenReturn(45);

        Optional<BookOrder> order = OrderFactory.getBookOrderByID();
        Mockito.when(orderRepository.findById(Mockito.any(Long.class))).thenReturn(order);

        List<BookOrder> bookOrders = OrderFactory.getOrdersByDates();
        Mockito.when(orderRepository.findByOrderDateBetween(Mockito.anyInt(),Mockito.anyInt())).thenReturn(bookOrders);
    }

    @Test
    public void save_order(){
        List<BookOrderDetailDTO> bookOrderDetailList = List.of(
                new BookOrderDetailDTO(null,"SAF",1),
                new BookOrderDetailDTO(null,"NUT",1),
                new BookOrderDetailDTO(null,"ALI",1)
        );
        BookOrderDate bookOrderDate = new BookOrderDate(1L, LocalDate.parse("2020-10-10"),10,10,2020,20201010);
        BookOrderDTO bookOrderDTO= new BookOrderDTO(1L,"4545",bookOrderDetailList, BigDecimal.valueOf(30),bookOrderDate);

        BookOrder retVal= orderServiceImpl.save(bookOrderDTO);
        assertNotNull(retVal);

    }

    @Test
    public void get_by_id(){
        BookOrder retVal= orderServiceImpl.getById("1");
        assertNotNull(retVal);
    }

    @Test
    public void list_order_by_date() throws Exception {
        List<BookOrderDTO> retVal= orderServiceImpl.listOrderByDate("2020-10-10","2020-11-10");
        assertNotNull(retVal);
    }
}
