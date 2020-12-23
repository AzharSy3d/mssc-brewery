package com.azharuworld.msscbrewery.web.controller;

import com.azharuworld.msscbrewery.services.CustomerService;
import com.azharuworld.msscbrewery.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId){
        return  new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> handlePost(@RequestBody CustomerDto customerDto){
        CustomerDto savedCustomer = customerService.saveCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location","/api/v1/customer"+savedCustomer.getId().toString());
        return  new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleUpdate(@PathVariable UUID beerId, CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable UUID beerId, CustomerDto customerDto){
        customerService.delete(customerDto);
    }


}
