package com.example.flightreservation.model.mapper;


import com.example.flightreservation.model.dto.SupplierDto;
import com.example.flightreservation.model.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupplierMapper extends BaseMapper<Supplier, SupplierDto> {
   SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);
}
