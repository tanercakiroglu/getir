package com.example.reading.is.good.service;

import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.request.CustomerSignUpRequest;

public interface ICustomerService {

    ApiResponse signUp(CustomerSignUpRequest request);

}
