package com.example.assignment2.service;

import com.example.assignment2.payload.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(int customerId);

    CustomerDto updateCustomer(int customerId, CustomerDto customerDto);

    void deleteCustomer(int customerId);
}
