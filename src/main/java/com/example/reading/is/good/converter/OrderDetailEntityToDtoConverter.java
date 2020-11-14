package com.example.reading.is.good.converter;

import com.example.reading.is.good.entity.OrderDetail;
import com.example.reading.is.good.model.OrderDetailDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderDetailEntityToDtoConverter implements Converter<OrderDetail, OrderDetailDto> {

    @Override
    public OrderDetailDto convert(OrderDetail orderDetail) {
        var product =orderDetail.getProduct();
        return OrderDetailDto.builder()
                .productName(product.getName())
                .author(product.getAuthor())
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())))
                .unitPrice(product.getPrice())
                .quantity(orderDetail.getQuantity())
                .build();
    }
}
