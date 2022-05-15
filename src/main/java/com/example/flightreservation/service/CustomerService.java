package com.example.flightreservation.service;

import com.example.flightreservation.model.dto.CustomerDto;
import com.example.flightreservation.model.request.CreateCustomerRequest;
import com.example.flightreservation.model.responce.CustomerResponse;

public interface CustomerService {
    CustomerDto create(CreateCustomerRequest request);
    CustomerResponse getById(Long id);
}
