package com.example.flightreservation.service;

import com.example.flightreservation.model.dto.PlaneDto;
import com.example.flightreservation.model.request.CreatePlaneRequest;
import org.springframework.stereotype.Service;

@Service
public interface PlaneService {
    PlaneDto create(CreatePlaneRequest request);
}
