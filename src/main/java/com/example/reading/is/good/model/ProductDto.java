package com.example.reading.is.good.model;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
@Builder
public class ProductDto implements Serializable {

    Long id;
    String name;
    String author;
    BigDecimal price;
    Long quantity;
}
