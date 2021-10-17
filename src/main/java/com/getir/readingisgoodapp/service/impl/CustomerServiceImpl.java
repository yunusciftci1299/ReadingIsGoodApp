package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.Constants.ErrorConstants;
import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.dto.CustomerDTO;
import com.getir.readingisgoodapp.dto.BookOrderDTO;
import com.getir.readingisgoodapp.exception.CustomerExistenceEception;
import com.getir.readingisgoodapp.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.getir.readingisgoodapp.repository.CustomerRepository;
import com.getir.readingisgoodapp.service.CustomerService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        log.debug("The Customer will be saved: {}",customerDTO);
        if(Objects.nonNull(customerRepository.findByCustomerNumber(customerDTO.getCustomerNumber())))
            throw new CustomerExistenceEception(ErrorConstants.CUSTOMER_ALREADY_EXIST+":"+customerDTO.getCustomerNumber());
        if(Objects.nonNull(customerRepository.findByEmail(customerDTO.getEmail())))
            throw new CustomerExistenceEception(ErrorConstants.CUSTOMER_ALREADY_EXIST+":"+customerDTO.getEmail());
        return customerRepository.save(new Customer().fromDTO(customerDTO));
    }

    @Override
    public List<BookOrderDTO> listByCustomerNumber(String customerNumber) throws Exception {
        try{
            List<BookOrderDTO> bookOrderDTOS = orderRepository.findAllByCustomerNumber(customerNumber)
                    .stream().map(order -> order.toDTO()).collect(Collectors.toList());
            return bookOrderDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
