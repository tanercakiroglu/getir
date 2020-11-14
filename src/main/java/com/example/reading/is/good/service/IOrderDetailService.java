package com.example.reading.is.good.service;

import com.example.reading.is.good.model.OrderDetailDto;

import java.util.List;

public interface IOrderDetailService {

  List<OrderDetailDto> getOrderDetailByOrderId(Long orderId) ;
}
