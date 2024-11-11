package com.spider.repos;

import com.spider.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomersRepo extends JpaRepository<Customers, Long> {

    Optional<Customers> findByEmail(String email);
    Optional<Customers> findByPhoneNumber(String phoneNumber);
}
