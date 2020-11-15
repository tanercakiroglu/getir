package com.example.reading.is.good.controller;

import com.example.reading.is.good.controller.impl.OrderController;
import com.example.reading.is.good.exception.InsufficientQuantityException;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.model.OrderDto;
import com.example.reading.is.good.request.PostOrderRequest;
import com.example.reading.is.good.service.impl.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Spy
    @InjectMocks
    private OrderController controller;

    @Mock
    private OrderService orderService;

    @Mock
    private Authentication authentication;



    @Test
    public void given_getOrdersByUser_thenReturnJson() {
        List<OrderDto> orderDtoList = new ArrayList<>();

        doReturn(orderDtoList).when(orderService).getOrdersByUser(any());
        ResponseEntity<ApiResponse> result = controller.getOrdersByUser( any());
        verify(orderService, times(1)).getOrdersByUser(any());
        assertEquals(200, result.getBody().getStatus());
        assertEquals(orderDtoList,result.getBody().getData());
    }

    @Test
    public void given_postOrdersByUser_thenReturnJson() throws InsufficientQuantityException {
        OrderDto.builder().build();
        doReturn("").when(authentication).getName();
        doReturn(OrderDto.builder().build()).when(orderService).postOrder(any(),any(),any());
        ResponseEntity<ApiResponse> result = controller.postOrder(new PostOrderRequest(),authentication);
        verify(orderService, times(1)).postOrder(any(),any(),any());
        assertEquals(200, result.getBody().getStatus());
        assertEquals(OrderDto.class,result.getBody().getData().getClass());

    }


}
