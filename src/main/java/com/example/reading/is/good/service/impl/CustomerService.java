package com.example.reading.is.good.service.impl;

import com.example.reading.is.good.converter.CustomerEntityConverter;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.repository.ICustomerRepository;
import com.example.reading.is.good.request.CustomerSignUpRequest;
import com.example.reading.is.good.service.ICustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomerService implements ICustomerService, UserDetailsService {

    private final ICustomerRepository customerRepository;
    private final CustomerEntityConverter customerEntityConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerService(ICustomerRepository customerRepository,
                           CustomerEntityConverter customerEntityConverter,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.customerEntityConverter = customerEntityConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public ApiResponse signUp(CustomerSignUpRequest request) {
      var customer = customerEntityConverter.convert(request);
      customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
      customerRepository.save(customer);
      return new ApiResponse();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
     return  customerRepository.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
