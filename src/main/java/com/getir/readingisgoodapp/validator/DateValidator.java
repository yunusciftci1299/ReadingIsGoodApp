package com.getir.readingisgoodapp.validator;

import com.getir.readingisgoodapp.dto.BookOrderDateDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
    public static boolean checkOrderDateIsValid(BookOrderDateDTO dto){
        try{
            if(dto.getOrderDate().getDayOfMonth() != dto.getOrderDay() ||
                    dto.getOrderDate().getMonthValue() !=dto.getOrderMonth() ||
                    dto.getOrderDate().getYear() != dto.getOrderYear() ||
                    Integer.parseInt(dto.getOrderYear()+""+dto.getOrderMonth()+""+dto.getOrderDay()) !=dto.getOrderDateIntValue()){
                return false;
            }
        }catch (Exception e){
            return false;
        }

        DateFormat sdf = new SimpleDateFormat(dto.getOrderDate().toString());
        sdf.setLenient(false);
        try {
            sdf.parse(dto.getOrderDate().toString());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

