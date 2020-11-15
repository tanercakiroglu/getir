package com.example.reading.is.good.repository;

import com.example.reading.is.good.entity.Customer;
import com.example.reading.is.good.entity.Order;
import com.example.reading.is.good.entity.OrderDetail;
import com.example.reading.is.good.entity.Product;
import com.example.reading.is.good.enumaration.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration
public class OrderRepositoryIntegrationTest {


    @Autowired
    private IOrderRepository orderRepository;

    @Test
    public void given_OrderSave_ThenReturnCustomer() {

        var customer =    Customer.builder()
                .name("abc")
                .password("$2a$10$gf/DoOCU1c.3trHuJGYQKOebxODx1pgYJS6n7DQvpIqXQ7kG5NgK.")
                .username("abc")
                .surname("34")
                .id(23L)
                .build();
        var product= Product.builder()
                .id(1L)
                .name("Vadideki Zambak")
                .quantity(34L)
                .author("Honoré de Balzac")
                .price(BigDecimal.valueOf(1000L))
                .build();
        var order= Order.builder()
                .orderStatus(OrderStatus.WAITING_FOR_PAYMENT)
                .customer(customer)
                .build();
        var orderDetail= OrderDetail.builder()
                .quantity(2L)
                .product(product)
                .order(order)
                .build();
        order.setOrderDetails(Collections.singletonList(orderDetail));

        var found = orderRepository.save(order);

        assertEquals(found.getOrderStatus(), order.getOrderStatus());
    }
}
