package com.example.reading.is.good.service;

import com.example.reading.is.good.exception.InsufficientQuantityException;
import com.example.reading.is.good.model.OrderDto;

import java.util.List;

public interface IOrderService {

    List<OrderDto> getOrdersByUsername(String username);

    OrderDto postOrder(Long productId, Long quantity, String username) throws InsufficientQuantityException;
}


