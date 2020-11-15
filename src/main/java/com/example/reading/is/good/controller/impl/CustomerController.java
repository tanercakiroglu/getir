package com.example.reading.is.good.controller.impl;

import com.example.reading.is.good.aspect.loggable.Loggable;
import com.example.reading.is.good.controller.ICustomerController;
import com.example.reading.is.good.exception.UserExistException;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.request.CustomerSignUpRequest;
import com.example.reading.is.good.service.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Loggable
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<ApiResponse> signUp(@Valid CustomerSignUpRequest request) throws UserExistException {
        customerService.signUp(request);
        return ResponseEntity.ok(new ApiResponse());
    }

}
