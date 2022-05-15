package com.example.flightreservation.controller;

import com.example.flightreservation.model.request.CreateBookingRequest;
import com.example.flightreservation.model.request.CreateFligthRequest;
import com.example.flightreservation.model.responce.CustomerFlightResponse;
import com.example.flightreservation.model.responce.MessageResponse;
import com.example.flightreservation.service.FlightService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flight")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightController {

    @NonNull FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateFligthRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightService.create(request));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate,
                                     @RequestParam("from") String cityFrom,
                                     @RequestParam("to") String cityTo) {

        List<CustomerFlightResponse> result = flightService.findAll(currentDate, cityFrom, cityTo);

        if (result.isEmpty()) {
            return ResponseEntity.ok(MessageResponse.sendMessage("No available flights found"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody CreateBookingRequest request) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(flightService.book(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> cancelBooking(@RequestBody CreateBookingRequest request) {
        return ResponseEntity
                .status(HttpStatus.GONE)
                .body(flightService.cancelBooking(request));
    }

}
