package com.example.reading.is.good.service.impl;

import com.example.reading.is.good.converter.CustomerEntityConverter;
import com.example.reading.is.good.exception.UserExistException;
import com.example.reading.is.good.repository.ICustomerRepository;
import com.example.reading.is.good.request.CustomerSignUpRequest;
import com.example.reading.is.good.service.ICustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    @Transactional
    public void signUp(CustomerSignUpRequest request) throws UserExistException {
        if(customerRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UserExistException("User already have an account","0002");
        }
        var customer = customerEntityConverter.convert(request);
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return customerRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
