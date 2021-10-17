package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.Constants.ErrorConstants;
import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.dto.BookDTO;
import com.getir.readingisgoodapp.exception.AvailableStockNegativeException;
import com.getir.readingisgoodapp.exception.BookExistenceException;
import com.getir.readingisgoodapp.exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.request.StockUpdateRequest;
import com.getir.readingisgoodapp.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO save(BookDTO dto) throws Exception {
        if(Objects.nonNull(bookRepository.findByCode(dto.getCode())))
            throw new BookExistenceException(ErrorConstants.BOOK_ALREADY_EXIST+":"+dto.getCode());
        try {
            log.debug("New Book record to save: {}",dto);
            return bookRepository.save(new Book().fromDTO(dto)).toDTO();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BookDTO updateStock(StockUpdateRequest request) throws Exception {
        if(request.getAvailableStock().compareTo(0)<0)
            throw new AvailableStockNegativeException(ErrorConstants.AVAILABLE_STOCK_NEGATIVE_ERROR);

        Book book = bookRepository.findByCode(request.getBookCode());
        if(Objects.isNull(book))
            throw new BookNotFoundException(ErrorConstants.BOOK_NOT_FOUND+":"+request.getBookCode());

        log.debug(String.format("The Book will be updated: %s - updating the stock as: %s",book,request.getAvailableStock()));

        try{
            book.setAvailableStock(request.getAvailableStock());
            return bookRepository.save(book).toDTO();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }


}
