package com.getir.readingisgoodapp.authentication.repository;
import com.getir.readingisgoodapp.authentication.model.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}