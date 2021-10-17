package com.getir.readingisgoodapp.serviceTests;

import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.dto.GroupYearAndMonthDTO;
import com.getir.readingisgoodapp.dto.StatisticDTO;
import com.getir.readingisgoodapp.factory.BookDoFactory;
import com.getir.readingisgoodapp.factory.CustomerFactory;
import com.getir.readingisgoodapp.factory.OrderFactory;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.repository.CustomerRepository;
import com.getir.readingisgoodapp.repository.OrderRepository;
import com.getir.readingisgoodapp.service.impl.OrderServiceImpl;
import com.getir.readingisgoodapp.service.impl.StatisticServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class StatisticServiceImplTest {
    private StatisticServiceImpl statisticService;
    private BookRepository bookRepository;
    private OrderRepository orderRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        orderRepository=mock(OrderRepository.class);
        bookRepository=mock(BookRepository.class);
        statisticService = new StatisticServiceImpl(orderRepository,bookRepository);

        List<GroupYearAndMonthDTO> groupYearAndMonthDTOList = OrderFactory.getGroupYearAndMonthDTOs();
        Mockito.when(orderRepository.getGroupYearAndMonthByCustomerNumber(Mockito.anyString())).thenReturn(groupYearAndMonthDTOList);

        List<BookOrder> bookOrders = OrderFactory.getOrdersByDates();
        Mockito.when(orderRepository.getOrdersByYearAndMonth(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString())).thenReturn(bookOrders);

    }

    @Test
    public void get_statistics(){
        List<StatisticDTO>  retVal= statisticService.getStatistics("1245");
        assertNotNull(retVal);
    }
}
