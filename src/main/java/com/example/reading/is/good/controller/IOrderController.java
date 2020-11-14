package com.example.reading.is.good.controller;

import com.example.reading.is.good.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@RequestMapping("/api/order")
public interface IOrderController {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getOrder(@PathParam("id") String id);
}
