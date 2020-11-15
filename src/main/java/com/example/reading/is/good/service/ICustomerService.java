package com.example.reading.is.good.service;

import com.example.reading.is.good.exception.UserExistException;
import com.example.reading.is.good.request.CustomerSignUpRequest;

public interface ICustomerService {

    void signUp(CustomerSignUpRequest request) throws UserExistException;

}
