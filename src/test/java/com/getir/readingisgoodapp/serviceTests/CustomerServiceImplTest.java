package com.getir.readingisgoodapp.serviceTests;

import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.dto.BookDTO;
import com.getir.readingisgoodapp.dto.BookOrderDTO;
import com.getir.readingisgoodapp.dto.CustomerDTO;
import com.getir.readingisgoodapp.factory.CustomerFactory;
import com.getir.readingisgoodapp.repository.CustomerRepository;
import com.getir.readingisgoodapp.repository.OrderRepository;
import com.getir.readingisgoodapp.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class CustomerServiceImplTest {
    private CustomerServiceImpl customerServiceImpl;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerRepository=mock(CustomerRepository.class);
        orderRepository=mock(OrderRepository.class);
        customerServiceImpl = new CustomerServiceImpl(customerRepository,orderRepository);

        Customer customer = CustomerFactory.getCustomer();
        Mockito.when(customerRepository.findByCustomerNumber(Mockito.anyString())).thenReturn(null);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        List<BookOrder> bookOrders=CustomerFactory.getOrdersCustomer();
        Mockito.when(orderRepository.findAllByCustomerNumber(Mockito.anyString())).thenReturn(bookOrders);
    }

    @Test
    public void save_customer(){
        CustomerDTO customerDTO = new CustomerDTO(1l,"TEST","TEST","4545","457");
        Customer retVal= customerServiceImpl.save(customerDTO);
        assertNotNull(retVal);
    }

    @Test
    public void list_by_customer_number() throws Exception {
        List<BookOrderDTO> retVal= customerServiceImpl.listByCustomerNumber("46456");
        assertNotNull(retVal);
    }
}
