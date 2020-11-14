package com.example.reading.is.good.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="orderDetails")
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 7552007440090911176L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

}
