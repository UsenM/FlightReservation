package com.example.flightreservation.controller;

import com.example.flightreservation.model.request.CreateCityRequest;
import com.example.flightreservation.service.CityService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/city")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityController {

    @NonNull CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCityRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cityService.create(request));
    }
}
