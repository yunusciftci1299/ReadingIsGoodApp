package com.getir.readingisgoodapp.repository;
import com.getir.readingisgoodapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book,Long> {
    Book findByCode(String code);

    @Query("SELECT b.availableStock from Book b where b.code=?1")
    Integer findAvailableStockByCode(String code);
}
