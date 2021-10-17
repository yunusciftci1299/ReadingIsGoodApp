package com.getir.readingisgoodapp.domain;

import com.getir.readingisgoodapp.dto.BookOrderDTO;
import com.getir.readingisgoodapp.dto.BookOrderDetailDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String customerNumber;

    @NotNull
    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true)
    private List<BookOrderDetail> bookOrderDetails;

    @NotNull
    private BigDecimal orderAmount;

    @NotNull
    @OneToOne(cascade={CascadeType.ALL}, orphanRemoval = true)
    private BookOrderDate bookOrderDate;

    public BookOrderDTO toDTO(){
        List<BookOrderDetailDTO> bookOrderDetailDTOS = bookOrderDetails.stream()
                .map(item->item.toDTO()).collect(Collectors.toList());
        return new BookOrderDTO(id,customerNumber, bookOrderDetailDTOS,orderAmount, bookOrderDate);
    }
    public BookOrder fromDTO(BookOrderDTO dto){
        List<BookOrderDetail> bookOrderDetails = dto.getBookOrderDetails().stream()
                .map(item->new BookOrderDetail().fromDTO(item)).collect(Collectors.toList());
        return new BookOrder(dto.getId(),dto.getCustomerNumber(),bookOrderDetails,dto.getOrderAmount(),dto.getBookOrderDate());
    }

}
