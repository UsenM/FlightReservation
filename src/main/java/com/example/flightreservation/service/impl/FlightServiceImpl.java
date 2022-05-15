package com.example.flightreservation.service.impl;

import com.example.flightreservation.exceptions.EntityNotFoundException;
import com.example.flightreservation.exceptions.FlightBookingException;
import com.example.flightreservation.exceptions.FundsException;
import com.example.flightreservation.model.dto.FlightDto;
import com.example.flightreservation.model.entity.City;
import com.example.flightreservation.model.entity.Customer;
import com.example.flightreservation.model.entity.Flight;
import com.example.flightreservation.model.entity.Plane;
import com.example.flightreservation.model.mapper.FlightMapper;
import com.example.flightreservation.model.request.CreateBookingRequest;
import com.example.flightreservation.model.request.CreateFligthRequest;
import com.example.flightreservation.model.responce.CityResponse;
import com.example.flightreservation.model.responce.CustomerFlightResponse;
import com.example.flightreservation.model.responce.MessageResponse;
import com.example.flightreservation.model.responce.PlaneResponse;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.service.CityService;
import com.example.flightreservation.service.CustomerService;
import com.example.flightreservation.service.FlightService;
import com.example.flightreservation.service.PlaneService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightServiceImpl implements FlightService {

    @NonNull FlightRepository flightRepository;
    @NonNull PlaneService planeService;
    @NonNull CityService cityService;
    @NonNull CustomerService customerService;

    @Override
    public FlightDto create(CreateFligthRequest request) {
        Plane plane = ((PlaneServiceImpl) planeService).getById(request.getPlaneId());
        City to = ((CityServiceImpl) cityService).getCityByStateAndName(request.getStateTo(), request.getCityTo());
        City from = ((CityServiceImpl) cityService).getCityByStateAndName(request.getStateFrom(), request.getCityFrom());

        Flight flight = Flight
                .builder()
                .to(to)
                .from(from)
                .flightDate(request.getFlightDate())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .plane(plane)
                .price(request.getPrice())
                .build();

        return FlightMapper.INSTANCE
                .toDto(flightRepository.save(flight));
    }

    @Override
    public List<CustomerFlightResponse> findAll(LocalDate currentDate, String cityFrom, String cityTo) {
        return flightRepository
                .findAllByFlightDateAndFromCityNameAndToCityName(currentDate, cityFrom, cityTo)
                .stream()
                .map(flight -> mapFlightToResponse(flight))
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse book(CreateBookingRequest request) {
        Flight flight = flightRepository
                .findById(request.getFlightId())
                .orElseThrow(() -> new EntityNotFoundException("Flight with id=" + request.getFlightId() + " not found"));

        Customer customer = ((CustomerServiceImpl) customerService).getEntityById(request.getCustomerId());

        int capacity = flight.getPlane().getCapacity();
        int size = flight.getPassengers().size();
        if (size >= capacity) {
            throw new FlightBookingException("No available seats: capacity=" + capacity + " filled=" + size);
        }

        if (customer.getWallet().getFunds().compareTo(flight.getPrice()) < 0) {
            throw new FundsException("Available funds=" + customer.getWallet().getFunds() + " ticket price=" + flight.getPrice());
        }

        flight.getPassengers().add(customer);
        customer.getWallet().setFunds(customer.getWallet().getFunds().subtract(flight.getPrice()));
        flightRepository.save(flight);

        return MessageResponse.sendMessage("Booking successful");
    }

    @Override
    public List<CustomerFlightResponse> getFlightsByCustomers(Long id) {
        Customer customers = ((CustomerServiceImpl) customerService).getEntityById(id);
        return flightRepository
                .findAllByPassenger(customers)
                .stream()
                .map(flight -> mapFlightToResponse(flight))
                .collect(Collectors.toList());
    }

    private CustomerFlightResponse mapFlightToResponse(Flight flight) {
        return CustomerFlightResponse
                .builder()
                .flightTime(flight.getArrivalTime())
                .id(flight.getId())
                .flightDate(flight.getFlightDate())
                .from(CityResponse
                        .builder()
                        .state(flight.getFrom().getState())
                        .cityName(flight.getFrom().getCityName())
                        .build())
                .to(CityResponse
                        .builder()
                        .state(flight.getTo().getState())
                        .cityName(flight.getTo().getCityName())
                        .build())
                .departureTime(flight.getDepartureTime())
                .plane(PlaneResponse
                        .builder()
                        .supplierName(flight.getPlane().getSupplier().getSupplierName())
                        .model(flight.getPlane().getModel())
                        .build())
                .price(flight.getPrice())
                .build();
    }

    @Override
    @Transactional
    public MessageResponse cancelBooking(CreateBookingRequest request) {
        flightRepository.deleteFromBooking(request.getCustomerId(), request.getFlightId());
        return MessageResponse.sendMessage("Flight canceled");
    }
}