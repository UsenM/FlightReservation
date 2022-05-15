package com.example.flightreservation.service.impl;

import com.example.flightreservation.exceptions.EntityNotFoundException;
import com.example.flightreservation.model.dto.SupplierDto;
import com.example.flightreservation.model.entity.Supplier;
import com.example.flightreservation.model.mapper.SupplierMapper;
import com.example.flightreservation.model.request.CreateSupplierRequest;
import com.example.flightreservation.repository.SupplierRepository;
import com.example.flightreservation.service.SupplierService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    @NonNull SupplierRepository supplierRepository;

    @Override
    public SupplierDto create(CreateSupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(request.getSupplierName());
        return SupplierMapper.INSTANCE
                .toDto(supplierRepository.save(supplier));
    }

    Supplier getSupplierByName(String name) {
        return supplierRepository
                .findSupplierBySupplierName(name)
                .orElseThrow(() -> new EntityNotFoundException("Supplier " + name + " not found"));
    }

}
