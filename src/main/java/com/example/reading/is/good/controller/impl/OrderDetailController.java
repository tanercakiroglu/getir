package com.example.reading.is.good.controller.impl;

import com.example.reading.is.good.aspect.loggable.Loggable;
import com.example.reading.is.good.controller.IOrderDetailController;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.service.IOrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Loggable
public class OrderDetailController implements IOrderDetailController {

    private final IOrderDetailService orderDetailService;

    public OrderDetailController(IOrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @Override
    public ResponseEntity<ApiResponse> getOrderDetailsByOrderId(Long orderId) {
        return ResponseEntity.ok(new ApiResponse(orderDetailService.getOrderDetailByOrderId(orderId)));
    }
}
