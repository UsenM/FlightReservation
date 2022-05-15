package com.example.flightreservation.controller;

import com.example.flightreservation.model.request.CreatePlaneRequest;
import com.example.flightreservation.service.PlaneService;
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
@RequestMapping("/api/v1/plane")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaneController {

    @NonNull PlaneService planeService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreatePlaneRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(planeService.create(request));
    }
}