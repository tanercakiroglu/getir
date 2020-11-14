package com.example.reading.is.good.converter;

import com.example.reading.is.good.entity.Customer;
import com.example.reading.is.good.request.CustomerSignUpRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityConverter implements Converter<CustomerSignUpRequest, Customer> {
    @Override
    public Customer  convert(CustomerSignUpRequest request) {
        return Customer.builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .password(request.getPassword())
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .build();
    }
}
