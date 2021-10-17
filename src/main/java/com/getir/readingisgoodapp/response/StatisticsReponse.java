package com.getir.readingisgoodapp.response;

import com.getir.readingisgoodapp.dto.StatisticDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatisticsReponse {
    private List<StatisticDTO> statisticDTOS ;

    public StatisticsReponse(List<StatisticDTO> statisticDTOS) {
        this.statisticDTOS = statisticDTOS;
    }
}
