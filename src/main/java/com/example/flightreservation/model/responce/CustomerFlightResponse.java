package com.example.flightreservation.model.responce;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerFlightResponse {
    Long id;
    LocalDate flightDate;
    LocalTime departureTime;
    LocalTime flightTime;
    BigDecimal price;
    CityResponse from;
    CityResponse to;
    PlaneResponse plane;
}
