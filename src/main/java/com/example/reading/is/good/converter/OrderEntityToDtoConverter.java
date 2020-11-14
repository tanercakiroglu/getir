package com.example.reading.is.good.converter;

import com.example.reading.is.good.entity.Order;
import com.example.reading.is.good.model.OrderDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderEntityToDtoConverter implements Converter<Order, OrderDto> {

    private final OrderDetailEntityToDtoConverter orderDetailEntityToDtoConverter;

    public OrderEntityToDtoConverter(OrderDetailEntityToDtoConverter orderDetailEntityToDtoConverter) {
        this.orderDetailEntityToDtoConverter = orderDetailEntityToDtoConverter;
    }

    @Override
    public OrderDto convert(Order order) {
        var orderDetails=order.getOrderDetails();
        return OrderDto.builder()
                .orderId(order.getId())
                .status(order.getOrderStatus())
                .orderDetailDtoList(orderDetails
                        .stream()
                        .map(orderDetailEntityToDtoConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    public OrderDto convertWithoutDetails(Order order){
        return OrderDto.builder()
                .orderId(order.getId())
                .status(order.getOrderStatus())
                .build();

    }

}
