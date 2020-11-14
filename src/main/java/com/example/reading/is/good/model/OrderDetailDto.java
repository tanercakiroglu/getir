package com.example.reading.is.good.model;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
@Builder
public class OrderDetailDto implements Serializable {

    private static final long serialVersionUID = 6942082599031435202L;
    String productName;
    String author;
    Long quantity;
    BigDecimal unitPrice;
    BigDecimal totalPrice;
}
