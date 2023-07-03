package com.example.assignment2.service.impl;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Order;
import com.example.assignment2.payload.OrderDto;
import com.example.assignment2.repositories.CustomerRepository;
import com.example.assignment2.repositories.OrderRepository;
import com.example.assignment2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());

        // Set other properties of the order entity
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + orderDto.getCustomerId()));
        order.setCustomer(customer);
        order.setOrderedAt(orderDto.getOrderedAt());
        // Set other properties of the order entity

        Order savedOrder = orderRepository.save(order);

        return mapOrderToDto(savedOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return mapOrderToDto(order);
        }
        throw new NoSuchElementException("Order not found with ID: " + orderId);
    }

    @Override
    public OrderDto updateOrder(int orderId, OrderDto orderDto) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setId(orderDto.getId());

            // Set other properties of the order entity
            Customer customer = customerRepository.findById(orderDto.getCustomerId())
                    .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + orderDto.getCustomerId()));
            order.setCustomer(customer);
            order.setOrderedAt(orderDto.getOrderedAt());


            Order updatedOrder = orderRepository.save(order);
            return mapOrderToDto(updatedOrder);
        }
        throw new NoSuchElementException("Order not found with ID: " + orderId);
    }

    @Override
    public void deleteOrder(int orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new NoSuchElementException("Order not found with ID: " + orderId);
        }
    }

    private OrderDto mapOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setOrderedAt(order.getOrderedAt());
        return orderDto;
    }
}
