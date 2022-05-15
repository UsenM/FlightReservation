package com.example.flightreservation.service;

import com.example.flightreservation.model.dto.FlightDto;
import com.example.flightreservation.model.request.CreateBookingRequest;
import com.example.flightreservation.model.request.CreateFligthRequest;
import com.example.flightreservation.model.responce.CustomerFlightResponse;
import com.example.flightreservation.model.responce.MessageResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface FlightService {
    FlightDto create(CreateFligthRequest request);
    List<CustomerFlightResponse> findAll(LocalDate currentDate, String cityFrom, String cityTo);
    MessageResponse book(CreateBookingRequest request);
    List<CustomerFlightResponse> getFlightsByCustomers(Long id);

    MessageResponse cancelBooking(CreateBookingRequest request);
}
