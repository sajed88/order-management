package com.example.assignment2.service.impl;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.payload.CustomerDto;
import com.example.assignment2.repositories.CustomerRepository;
import com.example.assignment2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);

        CustomerDto customerDto1 = mapToDTO(newCustomer);
        return  customerDto1;

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::mapCustomerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return mapCustomerToDto(customer);
        }
        throw new NoSuchElementException("Customer not found with ID: " + customerId);
    }

    @Override
    public CustomerDto updateCustomer(int customerId, CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setBornAt(customerDto.getBornAt());

            Customer updatedCustomer = customerRepository.save(customer);
            return mapCustomerToDto(updatedCustomer);
        }
        throw new NoSuchElementException("Customer not found with ID: " + customerId);
    }

    @Override
    public void deleteCustomer(int customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            throw new NoSuchElementException("Customer not found with ID: " + customerId);
        }
    }

    // Helper method to map Customer entity to CustomerDto
    private CustomerDto mapCustomerToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setBornAt(customer.getBornAt());
        return customerDto;
    }

    private CustomerDto mapToDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setBornAt(customer.getBornAt());


        return  customerDto;
    }

    private Customer mapToEntity(CustomerDto customerDto){
        Customer customers = new Customer();
        customers.setId(customerDto.getId());
        customers.setFirstName(customerDto.getFirstName());
        customers.setLastName(customerDto.getLastName());
        customers.setBornAt(customerDto.getBornAt());

        return customers;
    }



}


