package com.getir.readingisgoodapp.controller;

import com.getir.readingisgoodapp.Constants.EndPoints;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.getir.readingisgoodapp.request.BookRequest;
import com.getir.readingisgoodapp.request.StockUpdateRequest;
import com.getir.readingisgoodapp.response.BookResponse;
import com.getir.readingisgoodapp.service.BookService;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value= EndPoints.BOOK_API)
@Api(value="Book Api Documentation")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Save new book method",notes="Use this method if the book is not saved before")
    public BookResponse saveBook(@RequestBody  BookRequest bookRequest) throws Exception {
        return new BookResponse(bookService.save(bookRequest.getBookDTO()));
    }

    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update the stock of a book",notes="Use this method for the book is added before")
    public BookResponse updateStock(@RequestBody StockUpdateRequest request) throws Exception {
        log.debug("Update stock method has started - {}",new Date());
        return new BookResponse(bookService.updateStock(request));
    }

}
