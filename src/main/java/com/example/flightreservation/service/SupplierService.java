package com.example.flightreservation.service;

import com.example.flightreservation.model.dto.SupplierDto;
import com.example.flightreservation.model.request.CreateSupplierRequest;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService {
    SupplierDto create(CreateSupplierRequest request);
}