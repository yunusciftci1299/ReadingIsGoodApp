package com.getir.readingisgoodapp.domain;

import com.getir.readingisgoodapp.dto.BookDTO;
import com.getir.readingisgoodapp.dto.BookOrderDetailDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookCode;
    private Integer quantity;

    public BookOrderDetailDTO toDTO(){
        return new BookOrderDetailDTO(id,bookCode,quantity);
    }
    public BookOrderDetail fromDTO(BookOrderDetailDTO dto){
        return new BookOrderDetail(dto.getId(),dto.getBookCode(),dto.getQuantity());
    }
}
