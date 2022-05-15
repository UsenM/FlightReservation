package com.example.flightreservation.controller;

import com.example.flightreservation.model.request.CreateSupplierRequest;
import com.example.flightreservation.service.SupplierService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    @NonNull SupplierService supplierService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateSupplierRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(supplierService.create(request));
    }
}
