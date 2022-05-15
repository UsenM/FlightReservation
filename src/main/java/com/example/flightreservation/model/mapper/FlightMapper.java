package com.example.flightreservation.model.mapper;

import com.example.flightreservation.model.dto.FlightDto;
import com.example.flightreservation.model.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightMapper extends BaseMapper<Flight, FlightDto> {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);
}
