package com.example.flightreservation.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFligthRequest {
    LocalDate flightDate;
    LocalTime departureTime;
    LocalTime arrivalTime;
    BigDecimal price;
    String stateFrom;
    String cityFrom;
    String stateTo;
    String cityTo;
    Long planeId;
}
