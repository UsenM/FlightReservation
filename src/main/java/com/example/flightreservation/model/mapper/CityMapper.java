package com.example.flightreservation.model.mapper;


import com.example.flightreservation.model.dto.CityDto;
import com.example.flightreservation.model.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper extends BaseMapper<City, CityDto> {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}
