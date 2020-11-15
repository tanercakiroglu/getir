package com.example.reading.is.good.service.impl;

import com.example.reading.is.good.converter.OrderEntityToDtoConverter;
import com.example.reading.is.good.entity.Customer;
import com.example.reading.is.good.entity.Order;
import com.example.reading.is.good.entity.OrderDetail;
import com.example.reading.is.good.entity.Product;
import com.example.reading.is.good.enumaration.OrderStatus;
import com.example.reading.is.good.exception.InsufficientQuantityException;
import com.example.reading.is.good.model.OrderDto;
import com.example.reading.is.good.repository.ICustomerRepository;
import com.example.reading.is.good.repository.IOrderDetailRepository;
import com.example.reading.is.good.repository.IOrderRepository;
import com.example.reading.is.good.repository.IProductRepository;
import com.example.reading.is.good.service.IOrderService;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IProductRepository productRepository;
    private final ICustomerRepository customerRepository;
    private final OrderEntityToDtoConverter orderEntityToDtoConverter;

    public OrderService(IOrderRepository orderRepository, OrderEntityToDtoConverter orderEntityToDtoConverter, IProductRepository productRepository, ICustomerRepository customerRepository, IOrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderEntityToDtoConverter = orderEntityToDtoConverter;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrdersByUser(Long userId) {
        var orderList=orderRepository.findByCustomer(userId).orElseThrow(()-> new EntityNotFoundException("order not found"));
        return orderList.stream().map(orderEntityToDtoConverter::convertWithoutDetails).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto postOrder(Long productId, Long quantity,String username) throws InsufficientQuantityException {
       final var product = productRepository.findById(productId).orElseThrow(()->new EntityNotFoundException("product not found"));
        if (product.getQuantity() < quantity) {
            throw new InsufficientQuantityException("Product is not available for requested count", "0001");
        }
        product.setQuantity(product.getQuantity() - quantity);
        final var customer = customerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        final var order = prepareEntity(quantity, product, customer);
        return orderEntityToDtoConverter.convert(orderRepository.save(order));

    }

    @NotNull
    private Order prepareEntity(Long quantity, Product product, Customer customer) {
        var order= Order.builder()
                .orderStatus(OrderStatus.WAITING_FOR_PAYMENT)
                .customer(customer)
                .build();
        var orderDetail=OrderDetail.builder()
                .quantity(quantity)
                .product(product)
                .order(order)
                .build();
        order.setOrderDetails(Collections.singletonList(orderDetail));
        return order;
    }
}
