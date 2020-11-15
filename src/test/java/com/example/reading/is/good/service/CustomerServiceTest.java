package com.example.reading.is.good.service;

import com.example.reading.is.good.converter.CustomerEntityConverter;
import com.example.reading.is.good.entity.Customer;
import com.example.reading.is.good.exception.UserExistException;
import com.example.reading.is.good.repository.ICustomerRepository;
import com.example.reading.is.good.request.CustomerSignUpRequest;
import com.example.reading.is.good.service.impl.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class CustomerServiceTest {

    @Spy
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private CustomerEntityConverter customerEntityConverter;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void given_getCustomer_thenSaveSignUp() throws UserExistException {
        Customer customer = prepareCustomer();

        doReturn(customer).when(customerEntityConverter).convert(any());
        doReturn(Optional.empty()).when(customerRepository).findByUsername(anyString());
        customerService.signUp(new CustomerSignUpRequest());
        verify(customerService, times(1)).signUp(any());
        verify(bCryptPasswordEncoder, times(1)).encode(any());
        verify(customerEntityConverter, times(1)).convert(any());

    }

    @Test
    public void given_getUsername_thenLoadCustomer()  {
        UserDetails customer = prepareCustomer();
        doReturn(Optional.of(customer)).when(customerRepository).findByUsername(any());
        UserDetails result = customerService.loadUserByUsername(any());
        verify(customerService, times(1)).loadUserByUsername(any());
        assertEquals("adsad@com", result.getUsername());
        assertEquals("23sbwfrwkjrj332",result.getPassword());
    }

    @Test
    public void given_getUsername_thenThrowException()  {
        doThrow(new UsernameNotFoundException("user not found")).when(customerRepository).findByUsername(any());
        Assertions.assertThrows( UsernameNotFoundException.class, () -> {
            UserDetails result = customerService.loadUserByUsername(any());
            verify(customerService, times(1)).loadUserByUsername(any());
            assertEquals("adsad@com", result.getUsername());
            assertEquals("23sbwfrwkjrj332", result.getPassword());
        });
    }

    private Customer prepareCustomer() {
      return Customer.builder()
               .name("abc")
               .username("adsad@com")
               .password("23sbwfrwkjrj332")
               .id(23L)
               .build();
    }

}
