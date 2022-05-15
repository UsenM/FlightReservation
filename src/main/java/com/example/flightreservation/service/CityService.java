package com.example.flightreservation.service;

import com.example.flightreservation.model.dto.CityDto;
import com.example.flightreservation.model.request.CreateCityRequest;
import org.springframework.stereotype.Service;

@Service
public interface CityService {
    CityDto create(CreateCityRequest request);
}
