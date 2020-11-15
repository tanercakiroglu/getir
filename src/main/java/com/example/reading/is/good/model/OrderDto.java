package com.example.reading.is.good.model;

import com.example.reading.is.good.enumaration.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 258523888472007632L;

    Long orderId;
    OrderStatus status;
    List<OrderDetailDto> orderDetailDtoList;
}
