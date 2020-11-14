package com.example.reading.is.good.service.impl;

import com.example.reading.is.good.converter.OrderDetailEntityToDtoConverter;
import com.example.reading.is.good.model.OrderDetailDto;
import com.example.reading.is.good.repository.IOrderDetailRepository;
import com.example.reading.is.good.service.IOrderDetailService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService implements IOrderDetailService {

    private final IOrderDetailRepository orderDetailRepository;
    private final OrderDetailEntityToDtoConverter orderDetailEntityToDtoConverter;

    public OrderDetailService(IOrderDetailRepository orderDetailRepository, OrderDetailEntityToDtoConverter orderDetailEntityToDtoConverter) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailEntityToDtoConverter = orderDetailEntityToDtoConverter;
    }

    @Override
    @Transactional
    public List<OrderDetailDto> getOrderDetailByOrderId(Long orderId) {
        var orderDetailList = orderDetailRepository
                .findAllByOrder(orderId).orElseThrow(() -> new EntityNotFoundException("order detail not found"));
        return orderDetailList.stream().map(orderDetailEntityToDtoConverter::convert).collect(Collectors.toList());
    }
}
