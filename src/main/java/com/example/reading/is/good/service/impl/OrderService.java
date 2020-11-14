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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final OrderEntityToDtoConverter orderEntityToDtoConverter;
    private final IProductRepository productRepository;
    private final ICustomerRepository customerRepository;

    public OrderService(IOrderRepository orderRepository, OrderEntityToDtoConverter orderEntityToDtoConverter, IProductRepository productRepository, ICustomerRepository customerRepository, IOrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderEntityToDtoConverter = orderEntityToDtoConverter;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public List<OrderDto> getOrdersByUsername(String username) {
        var orderList=orderRepository.findByCustomer(username).orElseThrow(()-> new EntityNotFoundException("order not found"));
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
        final var order = new Order();
        final var orderDetail= new OrderDetail();
        orderDetail.setQuantity(quantity);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        order.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT);
        order.setCustomer(customer);
        order.setOrderDetails(Collections.singletonList(orderDetail));
        return order;
    }
}
