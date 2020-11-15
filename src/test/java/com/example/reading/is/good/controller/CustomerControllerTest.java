package com.example.reading.is.good.controller;

import com.example.reading.is.good.controller.impl.CustomerController;
import com.example.reading.is.good.exception.UserExistException;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.request.CustomerSignUpRequest;
import com.example.reading.is.good.service.impl.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Spy
    @InjectMocks
    private CustomerController controller;

    @Mock
    private CustomerService customerService;

    @Test
    public void given_SignUpRequest_thenReturnJson() throws UserExistException {

        doNothing().when(customerService).signUp( any());
        ResponseEntity<ApiResponse> result = controller.signUp( new CustomerSignUpRequest());
        verify(customerService, times(1)).signUp(any());
        assertEquals(200, result.getBody().getStatus());
    }

}
