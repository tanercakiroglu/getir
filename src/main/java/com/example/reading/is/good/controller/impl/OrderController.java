package com.example.reading.is.good.controller.impl;

import com.example.reading.is.good.aspect.loggable.Loggable;
import com.example.reading.is.good.controller.IOrderController;
import com.example.reading.is.good.exception.InsufficientQuantityException;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.request.PostOrderRequest;
import com.example.reading.is.good.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Loggable
public class OrderController implements IOrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<ApiResponse> getOrdersByUsername(String username) {
        return ResponseEntity.ok(new ApiResponse(orderService.getOrdersByUsername(username)));
    }

    @Override
    public ResponseEntity<ApiResponse> getOrder(@Valid PostOrderRequest request, Authentication authentication) throws InsufficientQuantityException {
        return ResponseEntity.ok(new ApiResponse(orderService.postOrder(request.getProductId(), request.getQuantity(), authentication.getName())));
    }
}


