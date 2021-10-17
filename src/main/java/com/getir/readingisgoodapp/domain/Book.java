package com.getir.readingisgoodapp.domain;

import com.getir.readingisgoodapp.dto.BookDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String code;

    private String name;

    @NotNull
    private BigDecimal price;

    private String description;

    @NotNull
    private Integer availableStock;

    public BookDTO toDTO(){
        return new BookDTO(id,code,name,price,description,availableStock);
    }
    public Book fromDTO(BookDTO dto){
        return new Book(dto.getId(),dto.getCode(),dto.getName(),
                dto.getPrice(),dto.getDescription(),dto.getAvailableStock());
    }
}
