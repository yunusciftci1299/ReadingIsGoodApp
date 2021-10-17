package com.getir.readingisgoodapp.service;

import com.getir.readingisgoodapp.dto.BookDTO;
import com.getir.readingisgoodapp.request.StockUpdateRequest;

public interface BookService {
    BookDTO save(BookDTO dto) throws Exception;
    BookDTO updateStock(StockUpdateRequest request) throws Exception;
}
