package com.example.flightreservation.model.mapper;


import com.example.flightreservation.model.dto.CustomerDto;
import com.example.flightreservation.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto>{
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
}
