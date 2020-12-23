package com.azharuworld.msscbrewery.services;

import com.azharuworld.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(UUID.randomUUID()).name("Azhar Syed").build();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        log.debug("Handle update");
    }

    @Override
    public void delete(CustomerDto customerDto) {
        log.debug("Handle Delete");
    }
}
