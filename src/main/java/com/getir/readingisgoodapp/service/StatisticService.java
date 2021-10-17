package com.getir.readingisgoodapp.service;

import com.getir.readingisgoodapp.dto.StatisticDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticService {
    List<StatisticDTO> getStatistics(String customerNumber);
}
