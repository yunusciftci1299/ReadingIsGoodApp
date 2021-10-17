package com.getir.readingisgoodapp.repository;

import com.getir.readingisgoodapp.domain.BookOrder;
import com.getir.readingisgoodapp.dto.GroupYearAndMonthDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<BookOrder,Long> {
    List<BookOrder> findAllByCustomerNumber(String customerNumber);

    @Query("SELECT ord from BookOrder ord where ord.bookOrderDate.orderDateIntValue >= :startIntValue or ord.bookOrderDate.orderDateIntValue <= :endIntValue")
    List<BookOrder> findByOrderDateBetween(@Param("startIntValue") Integer startIntValue, @Param("endIntValue")Integer endIntValue);

    @Query("SELECT new com.getir.readingisgoodapp.dto.GroupYearAndMonthDTO(o.bookOrderDate.orderYear, o.bookOrderDate.orderMonth) from BookOrder o where o.customerNumber=:customerNumber " +
            "group by o.bookOrderDate.orderYear,o.bookOrderDate.orderMonth")
    List<GroupYearAndMonthDTO> getGroupYearAndMonthByCustomerNumber(@Param("customerNumber") String customerNumber);

    @Query("SELECT ord from BookOrder ord where ord.customerNumber=:customerNumber and " +
            "ord.bookOrderDate.orderYear=:orderYear and ord.bookOrderDate.orderMonth=:orderMonth")
    List<BookOrder> getOrdersByYearAndMonth(@Param("orderMonth") Integer orderMonth, @Param("orderYear") Integer orderYear, @Param("customerNumber") String customerNumber);


}
