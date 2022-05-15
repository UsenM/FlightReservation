package com.example.flightreservation.model.mapper;

import com.example.flightreservation.model.dto.PlaneDto;
import com.example.flightreservation.model.entity.Plane;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaneMapper extends BaseMapper<Plane, PlaneDto> {
    PlaneMapper INSTANCE = Mappers.getMapper(PlaneMapper.class);
}
