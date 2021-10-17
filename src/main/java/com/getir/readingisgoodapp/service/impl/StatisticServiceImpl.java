package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.dto.GroupYearAndMonthDTO;
import com.getir.readingisgoodapp.dto.StatisticDTO;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.repository.OrderRepository;
import com.getir.readingisgoodapp.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    public StatisticServiceImpl(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<StatisticDTO> getStatistics(String customerNumber) {
        List<StatisticDTO> statisticDTOS = new ArrayList<>();
        List<GroupYearAndMonthDTO> groupYearAndMonthDTOS = orderRepository.getGroupYearAndMonthByCustomerNumber(customerNumber);
        groupYearAndMonthDTOS.forEach(item->{
            List<BookOrder> ordersByYearAndMonth=orderRepository.getOrdersByYearAndMonth(item.getMonth(), item.getYear(), customerNumber);
            if(ordersByYearAndMonth.size()>0){
                StatisticDTO statisticDTO = new StatisticDTO();
                statisticDTO.setYear(item.getYear().toString());
                statisticDTO.setMonth(new DateFormatSymbols().getMonths()[item.getMonth()-1]);
                statisticDTO.setTotalOrderCount(ordersByYearAndMonth.size());

                Double totalAmountPurchasedOrder =0.0;
                Integer totalCountPurchasedBook=0;
                for(BookOrder bookOrder : ordersByYearAndMonth){
                    totalAmountPurchasedOrder+=bookOrder.getOrderAmount().doubleValue();
                    totalCountPurchasedBook = totalCountPurchasedBook+ bookOrder.getBookOrderDetails().size();
                }
                statisticDTO.setTotalAmountPurchasedOrder(BigDecimal.valueOf(totalAmountPurchasedOrder));
                statisticDTO.setTotalCountPurchasedBook(totalCountPurchasedBook);

                statisticDTOS.add(statisticDTO);
            }
        });
        return statisticDTOS;
    }

}

