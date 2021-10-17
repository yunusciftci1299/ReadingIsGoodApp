package com.getir.readingisgoodapp.serviceTests;

import com.getir.readingisgoodapp.factory.BookDoFactory;
import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.dto.BookDTO;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.request.StockUpdateRequest;
import com.getir.readingisgoodapp.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class BookServiceImplTest {

    private BookServiceImpl bookServiceImpl;

    private BookRepository bookRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        bookRepository=mock(BookRepository.class);
        bookServiceImpl = new BookServiceImpl(bookRepository);

        Book book = BookDoFactory.getBook();
        Mockito.when(bookRepository.findByCode(Mockito.anyString())).thenReturn(null);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        Mockito.when(bookRepository.findByCode(Mockito.matches("UPD"))).thenReturn(book);
    }


    @Test
    public void save_dto() throws Exception {
        var bookDTO = new BookDTO();
        bookDTO.setCode("SAFAHAT");
        bookDTO.setDescription("Safahat book");
        bookDTO.setName("Safahat");
        bookDTO.setPrice(BigDecimal.valueOf(10));
        bookDTO.setAvailableStock(10);

        BookDTO retVal= bookServiceImpl.save(bookDTO);
        assertNotNull(retVal);
    }

    @Test
    public void update_Stock() throws Exception {
        var stockUpdateRequest = new StockUpdateRequest();
        stockUpdateRequest.setAvailableStock(10);
        stockUpdateRequest.setBookCode("UPD");

        BookDTO retVal= bookServiceImpl.updateStock(stockUpdateRequest);
        assertNotNull(retVal);
    }
}
