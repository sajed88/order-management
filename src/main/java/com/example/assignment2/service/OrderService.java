package com.example.assignment2.service;

import com.example.assignment2.payload.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(int orderId);

    OrderDto updateOrder(int orderId, OrderDto orderDto);

    void deleteOrder(int orderId);
}
