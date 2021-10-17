package com.getir.readingisgoodapp.controller;

import com.getir.readingisgoodapp.Constants.EndPoints;
import com.getir.readingisgoodapp.response.StatisticsReponse;
import com.getir.readingisgoodapp.service.StatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= EndPoints.STATISTICS_API)
@Api(value="Statistics Api Documentation")
public class StatisticsController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    public StatisticsController(StatisticService statisticService){
        this.statisticService=statisticService;
    }

    @GetMapping(value="/{customerNumber}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get customer's monthly statistics",notes="This method will serve customer’s monthly order statistics")
    public StatisticsReponse getStatistics(@PathVariable("customerNumber") String customerNumber){
        return new StatisticsReponse(statisticService.getStatistics(customerNumber));
    }
}
