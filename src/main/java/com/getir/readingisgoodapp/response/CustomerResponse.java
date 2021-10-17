package com.getir.readingisgoodapp.response;

import com.getir.readingisgoodapp.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    private CustomerDTO customerDTO;

    public CustomerResponse(CustomerDTO dto){
        customerDTO=dto;
    }
}
