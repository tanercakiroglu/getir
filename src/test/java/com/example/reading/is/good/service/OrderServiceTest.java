package com.example.reading.is.good.service;

import com.example.reading.is.good.converter.OrderEntityToDtoConverter;
import com.example.reading.is.good.entity.Customer;
import com.example.reading.is.good.entity.Order;
import com.example.reading.is.good.entity.Product;
import com.example.reading.is.good.enumaration.OrderStatus;
import com.example.reading.is.good.exception.InsufficientQuantityException;
import com.example.reading.is.good.model.OrderDto;
import com.example.reading.is.good.repository.ICustomerRepository;
import com.example.reading.is.good.repository.IOrderRepository;
import com.example.reading.is.good.repository.IProductRepository;
import com.example.reading.is.good.service.impl.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class OrderServiceTest {

    @Spy
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderEntityToDtoConverter orderEntityToDtoConverter;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private Authentication authentication;


    @Test
    public void given_postOrder_thenReturnSavedOrder() throws InsufficientQuantityException {
        var product = prepareProduct(34);
        var customer = prepareCustomer();
        var order = prepareOrder(customer);
        OrderDto orderDto = OrderDto.builder().status(OrderStatus.WAITING_FOR_PAYMENT).orderId(1L).build();
        doReturn(Optional.of(product)).when(productRepository).findById(any());
        doReturn(Optional.of(customer)).when(customerRepository).findByUsername(any());
        doReturn(orderDto).when(orderEntityToDtoConverter).convert(any());
        doReturn(order).when(orderRepository).save(any());
        doReturn("").when(authentication).getName();
        OrderDto result = orderService.postOrder(1L, 2L, "");
        verify(orderService, times(1)).postOrder(any(), any(), any());
        assertEquals(OrderStatus.WAITING_FOR_PAYMENT, result.getStatus());
        assertEquals(1L, result.getOrderId());

    }

    @Test
    public void given_postOrdersByUser_thenInsufficientQuantityException() {
        var product = prepareProduct(1);
        var customer = prepareCustomer();
        var order = prepareOrder(customer);
        OrderDto orderDto = OrderDto.builder().status(OrderStatus.WAITING_FOR_PAYMENT).orderId(1L).build();
        Assertions.assertThrows(InsufficientQuantityException.class, () -> {
            doReturn(Optional.of(product)).when(productRepository).findById(any());
            doReturn(Optional.of(customer)).when(customerRepository).findByUsername(any());
            doReturn(orderDto).when(orderEntityToDtoConverter).convert(any());
            doReturn(order).when(orderRepository).save(any());
            doReturn("").when(authentication).getName();
            OrderDto result = orderService.postOrder(1L, 2L, "");
            verify(orderService, times(1)).postOrder(any(), any(), any());
            assertEquals(OrderStatus.WAITING_FOR_PAYMENT, result.getStatus());
            assertEquals(1L, result.getOrderId());
        });

    }

    @Test
    public void given_getOrdersByUserName_thenReturnOrderDetailList() {
        var customer = prepareCustomer();
        var order = prepareOrder(customer);

        doReturn(Optional.of(Arrays.asList(order))).when(orderRepository).findByCustomer(any());
        doReturn("").when(authentication).getName();
        List<OrderDto> result = orderService.getOrdersByUser(any());
        verify(orderService, times(1)).getOrdersByUser(any());
        assertEquals(1, result.size());
    }

    private Order prepareOrder(Customer customer) {
        return Order.builder()
                .orderStatus(OrderStatus.WAITING_FOR_PAYMENT)
                .id(1L)
                .build();
    }

    private Product prepareProduct(long i) {
        return Product.builder()
                .name("abc")
                .quantity(i)
                .author("def")
                .build();
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
