package com.example.reading.is.good.entity;

import com.example.reading.is.good.config.AuditEntityListener;
import com.example.reading.is.good.enumaration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@EntityListeners(AuditEntityListener.class)
@Entity(name="orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -4781925702178869690L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    public Order() {
        this.setOrderDate(LocalDateTime.now());
    }
}

