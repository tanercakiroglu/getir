package com.example.reading.is.good.repository;

import com.example.reading.is.good.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository  extends JpaRepository<OrderDetail,Long> {
}
