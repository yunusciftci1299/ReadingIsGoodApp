package com.getir.readingisgoodapp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgoodapp.dto.BookDTO;
import com.getir.readingisgoodapp.request.BookRequest;
import com.getir.readingisgoodapp.request.StockUpdateRequest;
import com.getir.readingisgoodapp.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

public class BookControllerTest {
    private BookController bookController;
    private BookService bookService;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        bookService=mock(BookService.class);
        bookController = new BookController(bookService);
    }

    @Test
    public void save_book() throws Exception {
        ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        BookDTO bookDTO = new BookDTO(0L,"ALI","ALICE", BigDecimal.valueOf(15),"Alice in boerderland",41);
        var bookRequest = new BookRequest();
        bookRequest.setBookDTO(bookDTO);
        String json = ow.writeValueAsString(bookRequest);

        mockMvc= MockMvcBuilders.standaloneSetup(bookController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/book/save")
                .content(MediaType.APPLICATION_JSON_VALUE).content(json)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void stock_update() throws Exception {
        ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        var stockUpdateRequest = new StockUpdateRequest();
        stockUpdateRequest.setBookCode("HEIDI");
        stockUpdateRequest.setAvailableStock(5);
        String json = ow.writeValueAsString(stockUpdateRequest);
        mockMvc= MockMvcBuilders.standaloneSetup(bookController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/book/update")
                        .content(MediaType.APPLICATION_JSON_VALUE).content(json)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
