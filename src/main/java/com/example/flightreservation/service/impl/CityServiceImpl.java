package com.example.flightreservation.service.impl;

import com.example.flightreservation.exceptions.EntityNotFoundException;
import com.example.flightreservation.model.dto.CityDto;
import com.example.flightreservation.model.entity.City;
import com.example.flightreservation.model.mapper.CityMapper;
import com.example.flightreservation.model.request.CreateCityRequest;
import com.example.flightreservation.repository.CityRepository;
import com.example.flightreservation.service.CityService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityServiceImpl implements CityService {

    @NonNull CityRepository cityRepository;

    City getCityByStateAndName(String state, String city) {
        return cityRepository
                .findCityByStateAndCityName(state, city)
                .orElseThrow(() -> new EntityNotFoundException("City with name=" + city + " and state=" + state + " not found"));
    }

    @Override
    public CityDto create(CreateCityRequest request) {
        City city = City
                .builder()
                .cityName(request.getCityName())
                .lat(request.getLat())
                .lon(request.getLon())
                .state(request.getState())
                .build();
        return CityMapper.INSTANCE.toDto(cityRepository.save(city));
    }
}
