package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.Constants.ErrorConstants;
import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.dto.BookOrderDTO;
import com.getir.readingisgoodapp.dto.BookOrderDetailDTO;
import com.getir.readingisgoodapp.exception.*;
import com.getir.readingisgoodapp.validator.DateValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.repository.CustomerRepository;
import com.getir.readingisgoodapp.repository.OrderRepository;
import com.getir.readingisgoodapp.service.OrderService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public OrderServiceImpl(BookRepository bookRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public BookOrder save(BookOrderDTO dto) {
        requestOrderValidation(dto);
        dto.getBookOrderDetails().forEach(orderDetail -> {
            Book book = bookRepository.findByCode(orderDetail.getBookCode());
            book.setAvailableStock(book.getAvailableStock()-orderDetail.getQuantity());
            bookRepository.save(book);
        });
        var order = new BookOrder().fromDTO(dto);
        log.debug("The order will be saved  : {}",order);
        return orderRepository.save(order);
    }

    @Override
    public BookOrder getById(String id) {
        BookOrder bookOrder;
        try{
            bookOrder=orderRepository.findById(Long.valueOf(id))
                    .orElseThrow(()->new OrderNotFoundException(ErrorConstants.ORDER_NOT_FOUND));
        }catch (NumberFormatException e){
            throw new InvalidIDTypeException(ErrorConstants.INVALID_TYPE_ID);
        }catch (Exception e){
            throw e;
        }
        return bookOrder;
    }

    @Override
    public List<BookOrderDTO> listOrderByDate(String startDate, String endDate) throws Exception {
        List<BookOrderDTO> bookOrderDTOList;
        try{
            LocalDate startLocalDate=LocalDate.parse(startDate);
            LocalDate endLocalDate=LocalDate.parse(endDate);
            Integer startDateIntValue=Integer.parseInt(startLocalDate.getYear()+""+startLocalDate.getMonthValue()+""+startLocalDate.getDayOfMonth());
            Integer endDateIntValue=Integer.parseInt(endLocalDate.getYear()+""+endLocalDate.getMonthValue()+""+endLocalDate.getDayOfMonth());
            bookOrderDTOList = orderRepository.findByOrderDateBetween(startDateIntValue,endDateIntValue)
                    .stream().map(order ->order.toDTO())
                    .collect(Collectors.toList());
            log.debug("The bookOrderDTOList size bewteen dates  : {} {}-{}",bookOrderDTOList.size(),startDateIntValue,endDateIntValue);

        }catch (DateTimeParseException e){
            throw new InvalidDateException(ErrorConstants.INVALID_DATE_TYPE);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return bookOrderDTOList;
    }

    public void requestOrderValidation(BookOrderDTO dto){
        log.debug("The requestOrderValidation dto: {}",dto);

        if(Objects.isNull(customerRepository.findByCustomerNumber(dto.getCustomerNumber()))){
            throw new CustomerNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND);
        }

        if(!dto.getBookOrderDetails().stream().allMatch(orderDetail -> orderDetail.getQuantity()>0)){
            throw new QuantityNegativeException(ErrorConstants.QUANTITY_NEGATIVE_ERROR);
        }

        if(!dto.getBookOrderDetails().stream().allMatch(orderDetail -> {
            Integer availableStock = bookRepository.findAvailableStockByCode(orderDetail.getBookCode());
            return availableStock.compareTo(orderDetail.getQuantity()) >= 0;})){
            throw new StockNotAvailableException(ErrorConstants.STOCK_NOT_AVAILABLE);
        }

        Double totalAmountOfBooks = 0.0;
        for(BookOrderDetailDTO bookOrderDetailDTO: dto.getBookOrderDetails()){
            Book book = bookRepository.findByCode(bookOrderDetailDTO.getBookCode());
            totalAmountOfBooks+=book.getPrice().multiply(BigDecimal.valueOf(bookOrderDetailDTO.getQuantity())).doubleValue();
        }
        if(BigDecimal.valueOf(totalAmountOfBooks).compareTo(dto.getOrderAmount()) != 0){
            throw new TotalOrderAmountWrongException(ErrorConstants.TOTAL_ORDER_AMOUNT_WRONG);
        }
        if(!DateValidator.checkOrderDateIsValid(dto.getBookOrderDate().toDTO()))
            throw new InvalidEntityBookOrderDateException(ErrorConstants.INVALID_ORDER_DATE_ENTITY);

    }

}
