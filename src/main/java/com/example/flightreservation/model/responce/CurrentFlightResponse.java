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
public class CurrentFlightResponse {
    Long id;
    LocalDate flightDate;
    LocalTime departureTime;
    LocalTime arrivalTime;
    BigDecimal price;

    Long cityFromId;
    String cityFromName;
    String cityFromState;

    Long cityToId;
    String cityToName;
    String cityToState;

}
