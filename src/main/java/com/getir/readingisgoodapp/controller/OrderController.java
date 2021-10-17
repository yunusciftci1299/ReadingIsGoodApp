package com.getir.readingisgoodapp.controller;

import com.getir.readingisgoodapp.Constants.EndPoints;
import com.getir.readingisgoodapp.response.ListOrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.getir.readingisgoodapp.request.OrderRequest;
import com.getir.readingisgoodapp.response.OrderResponse;
import com.getir.readingisgoodapp.service.OrderService;

@Slf4j
@RestController
@RequestMapping(value= EndPoints.ORDER_API)
@Api(value="Order Api Documentation")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }


    @PostMapping(value="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Save new order method",notes="Use this method for placing orders")
    public OrderResponse save(@RequestBody OrderRequest request){
        return new OrderResponse(orderService.save(request.getBookOrderDTO()).toDTO());
    }

    @GetMapping(value="/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get an order by id",notes="Use this method if an order is registered before.")
    public OrderResponse getById(@PathVariable("id") String id){
        return new OrderResponse(orderService.getById(id).toDTO());
    }

    @GetMapping(value="/{startOrderDate}/{endOrderDate}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get orders between order dates",notes="Use this method to get orders between requested dates")
    public ListOrderResponse getById(@PathVariable("startOrderDate") String startOrderDate, @PathVariable("endOrderDate") String endOrderDate) throws Exception {
        log.debug("Getting orders between dates: {} -{}",startOrderDate,endOrderDate);
        return new ListOrderResponse(orderService.listOrderByDate(startOrderDate,endOrderDate));
    }
}
