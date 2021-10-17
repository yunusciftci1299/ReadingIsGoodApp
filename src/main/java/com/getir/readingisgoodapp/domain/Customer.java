package com.getir.readingisgoodapp.domain;

import com.getir.readingisgoodapp.dto.CustomerDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"customerNumber"}))
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique=true)
    private String email;

    private String phoneNumber;

    @NotNull
    private String customerNumber;

    public CustomerDTO toDTO(){
        return new CustomerDTO(id,name,email,phoneNumber,customerNumber);
    }
    public Customer fromDTO(CustomerDTO dto){
        return new Customer(dto.getId(),dto.getName(),dto.getEmail(),dto.getPhoneNumber(),dto.getCustomerNumber());
    }

}
