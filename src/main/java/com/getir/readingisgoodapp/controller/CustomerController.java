package com.getir.readingisgoodapp.controller;

import com.getir.readingisgoodapp.Constants.EndPoints;
import com.getir.readingisgoodapp.dto.BookOrderDTO;
import com.getir.readingisgoodapp.request.PaginationCustomerOrderRequest;
import com.getir.readingisgoodapp.response.PagedListOrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.getir.readingisgoodapp.request.CustomerRequest;
import com.getir.readingisgoodapp.response.CustomerResponse;
import com.getir.readingisgoodapp.service.CustomerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value= EndPoints.CUSTOMER_API)
@Api(value="Customer Api Documentation")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Save new customer method",notes="Use this method if the customer is not saved before")
    public CustomerResponse save(@RequestBody CustomerRequest request){
        return new CustomerResponse(customerService.save(request.getCustomerDTO()).toDTO());
    }

    @PostMapping(value = "/orders",consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get customer orders method",notes="Use this method if the customer has orders")
    public PagedListOrderResponse getOrdersByCustomerNumber (@RequestBody PaginationCustomerOrderRequest request) throws Exception {
        List<BookOrderDTO> bookOrderDTOS = customerService.listByCustomerNumber(request.getCustomerNumber());
        log.info("The customer's orders count:",bookOrderDTOS.size());
        Pageable pageable =PageRequest.of(request.getPage(),request.getPageSize());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), bookOrderDTOS.size());
        return new PagedListOrderResponse(new PageImpl<>(bookOrderDTOS.subList(start,end),pageable, bookOrderDTOS.size()));
    }

}
