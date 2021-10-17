package com.getir.readingisgoodapp.service;

import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.dto.BookOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    BookOrder save(BookOrderDTO dto);
    BookOrder getById(String id);
    List<BookOrderDTO> listOrderByDate(String startDate, String endDate) throws Exception;
}
