package com.example.flightreservation.model.responce;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerResponse {
    Long id;
    String email;
    String firstName;
    String lastName;
    String patronymic;
    String fullName;
    String phoneNumber;
    BigDecimal funds;
    List<CustomerFlightResponse> flightResponseList;
}
