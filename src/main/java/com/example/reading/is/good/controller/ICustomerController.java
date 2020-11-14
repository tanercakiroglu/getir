package com.example.reading.is.good.controller;

import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.request.CustomerSignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/customer")
public interface ICustomerController {

    @PostMapping("/sign-up")
    ResponseEntity<ApiResponse> signUp(@Valid @RequestBody CustomerSignUpRequest request);


}
