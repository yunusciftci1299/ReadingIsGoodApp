package com.getir.readingisgoodapp.repository;

import com.getir.readingisgoodapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomerNumber(String sutomerNumber);
    Customer findByEmail(String email);

}
