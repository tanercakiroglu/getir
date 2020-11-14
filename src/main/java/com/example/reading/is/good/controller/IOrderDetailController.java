package com.example.reading.is.good.controller;

import com.example.reading.is.good.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/order-detail")
public interface IOrderDetailController {

    @GetMapping("{orderId}")
    ResponseEntity<ApiResponse> getOrderDetailsByOrderId(@PathVariable Long orderId);
}
