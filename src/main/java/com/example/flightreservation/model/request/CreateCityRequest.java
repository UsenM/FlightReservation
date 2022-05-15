package com.example.flightreservation.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCityRequest {
    String cityName;
    String state;
    BigDecimal lat;
    BigDecimal lon;
}
