package com.spider.repos;

import com.spider.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

    List<Orders> findByCustomerId(Long customerId);
    Optional<Orders> findByTrackingNumber(String trackingNumber);
    List<Orders> findByStatus(String status);
}
