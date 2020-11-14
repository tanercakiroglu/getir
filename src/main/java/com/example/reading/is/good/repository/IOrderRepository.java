package com.example.reading.is.good.repository;

import com.example.reading.is.good.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository  extends JpaRepository<Order,Long> {
}
