package com.example.reading.is.good.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="products")
public class Product implements Serializable {

    private static final long serialVersionUID = 5068420570210387667L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private BigDecimal price;

    private Long quantity;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;

}
