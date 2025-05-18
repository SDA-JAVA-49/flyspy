package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.Aircraft;
import al.sda.flyspy.domain.flight.model.dto.airdata.Airline;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightIdentifier;
import al.sda.flyspy.domain.flight.model.dto.airdata.TerminalPoint;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.Repository;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FlightService {
    private final Repository<Flight, Long> repository;
    private final AirDataService airDataService;
    private static final String DEFAULT_VALUE = "-";

    public FlightService(Repository<Flight, Long> repository, AirDataService airDataService) {
        this.repository = repository;
        this.airDataService = airDataService;
    }

    public List<FlightDto> getArrivingFlights() throws URISyntaxException {
        List<FlightDto> result = new ArrayList<>();
        List<AirData> arrivingFlights = airDataService.getFlights(Map.of("arr_iata", "TIA"));
        for (AirData flight : arrivingFlights) {
            FlightDto flightDto = convertToFlightDto(flight);
            result.add(flightDto);
        }
        return result;

        //TODO: Store data in DB
        //TODO: convert from AirData to FlightDto
    }

    public List<FlightDto> getDepartingFlights() throws URISyntaxException {
        List<FlightDto> result = new ArrayList<>();
        List<AirData> departingFlights = airDataService.getFlights(Map.of("dep_iata", "TIA"));
        for (AirData flight : departingFlights) {
            FlightDto flightDto = convertToFlightDto(flight);
            result.add(flightDto);
        }
        return result;
    }

    public FlightDto convertToFlightDto(AirData airData) {
        if (airData == null) {
            throw new IllegalArgumentException("AirData is null");
        }
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber(Optional.of(airData)
                .map(AirData::getFlight)
                .map(FlightIdentifier::getNumber)
                .orElse("-"));

        Optional<TerminalPoint> departure = Optional.of(airData).map(AirData::getDeparture);
        flightDto.setDepartureAirport(departure.map(TerminalPoint::getIata).orElse(DEFAULT_VALUE));
        flightDto.setDepartureTime(departure.map(TerminalPoint::getScheduled).orElse(DEFAULT_VALUE));
        Optional<TerminalPoint> arrival = Optional.of(airData).map(AirData::getArrival);
        flightDto.setArrivalAirport(arrival.map(TerminalPoint::getIata).orElse(DEFAULT_VALUE));
        flightDto.setArrivalTime(arrival.map(TerminalPoint::getScheduled).orElse(DEFAULT_VALUE));
        flightDto.setAirlineName(Optional.of(airData)
                .map(AirData::getAirline)
                .map(Airline::getName)
                .orElse(DEFAULT_VALUE));
        flightDto.setAircraftRegistration(Optional.of(airData)
                .map(AirData::getAircraft)
                .map(Aircraft::getRegistration)
                .orElse(DEFAULT_VALUE));

        return flightDto;
    }
}

