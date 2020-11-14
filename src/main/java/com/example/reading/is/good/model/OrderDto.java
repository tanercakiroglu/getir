package com.example.reading.is.good.model;

import com.example.reading.is.good.enumaration.OrderStatus;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
@Builder
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 258523888472007632L;

    Long orderId;
    OrderStatus status;
    List<OrderDetailDto> orderDetailDtoList;
}
