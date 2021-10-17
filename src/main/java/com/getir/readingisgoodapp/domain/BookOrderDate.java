package com.getir.readingisgoodapp.domain;

import com.getir.readingisgoodapp.dto.BookOrderDateDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookOrderDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private Integer orderDay;

    @NotNull
    private Integer orderMonth;

    @NotNull
    private Integer orderYear;

    @NotNull
    private Integer orderDateIntValue;

    public BookOrderDateDTO toDTO(){
        return new BookOrderDateDTO(id,orderDate,orderDay,orderMonth,orderYear,orderDateIntValue);
    }
    public BookOrderDate fromDTO(BookOrderDateDTO dto){
        return new BookOrderDate(dto.getId(),dto.getOrderDate(),dto.getOrderDay(),dto.getOrderMonth(),dto.getOrderYear(),dto.getOrderDateIntValue());
    }

}
