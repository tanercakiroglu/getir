package com.example.reading.is.good.repository;

import com.example.reading.is.good.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository  extends JpaRepository<Order,Long> {

    @Query("select o from orders o where o.customer.id=:userId")
    Optional<List<Order>> findByCustomer(Long userId);
}
