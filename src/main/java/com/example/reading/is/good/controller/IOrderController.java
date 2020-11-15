package com.example.reading.is.good.controller;

import com.example.reading.is.good.exception.InsufficientQuantityException;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.request.PostOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/order")
public interface IOrderController {

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse> getOrdersByUser(@PathVariable Long userId);

    @PostMapping("/")
    ResponseEntity<ApiResponse> postOrder(@Valid @RequestBody PostOrderRequest request, Authentication authentication) throws InsufficientQuantityException;
}
