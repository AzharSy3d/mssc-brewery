package com.azharuworld.msscbrewery.services;

import com.azharuworld.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void updateCustomer(CustomerDto customerDto);

    void delete(CustomerDto customerDto);
}
