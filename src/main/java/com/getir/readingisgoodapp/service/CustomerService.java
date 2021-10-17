package com.getir.readingisgoodapp.service;

import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.dto.CustomerDTO;
import com.getir.readingisgoodapp.dto.BookOrderDTO;

import java.util.List;

public interface CustomerService {
    Customer save(CustomerDTO customerDTO);
    List<BookOrderDTO> listByCustomerNumber(String customerNumber) throws Exception;
}
