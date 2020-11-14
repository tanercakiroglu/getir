package com.example.reading.is.good.repository;

import com.example.reading.is.good.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderDetailRepository  extends JpaRepository<OrderDetail,Long> {

    @Query("select o from order_details o where o.order.id=:orderId")
    Optional<List<OrderDetail>> findAllByOrder(Long orderId);
}
