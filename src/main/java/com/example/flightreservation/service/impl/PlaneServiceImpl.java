package com.example.flightreservation.service.impl;

import com.example.flightreservation.exceptions.EntityNotFoundException;
import com.example.flightreservation.model.dto.PlaneDto;
import com.example.flightreservation.model.entity.Plane;
import com.example.flightreservation.model.mapper.PlaneMapper;
import com.example.flightreservation.model.request.CreatePlaneRequest;
import com.example.flightreservation.repository.PlaneRepository;
import com.example.flightreservation.service.PlaneService;
import com.example.flightreservation.service.SupplierService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaneServiceImpl implements PlaneService {

    @NonNull PlaneRepository planeRepository;
    @NonNull SupplierService supplierService;

    Plane getById(Long id) {
        return planeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plane with id=" + id + " not found"));
    }

    @Override
    public PlaneDto create(CreatePlaneRequest request) {
        return PlaneMapper.INSTANCE
                .toDto(planeRepository
                        .save(Plane
                                .builder()
                                .supplier(((SupplierServiceImpl) supplierService).getSupplierByName(request.getSupplier()))
                                .capacity(request.getCapacity())
                                .model(request.getModel())
                                .build()));
    }
}
