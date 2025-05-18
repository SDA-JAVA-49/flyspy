package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.Aircraft;
import al.sda.flyspy.domain.flight.model.dto.airdata.Airline;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightDataResponse;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightIdentifier;
import al.sda.flyspy.domain.flight.model.dto.airdata.TerminalPoint;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.util.Repository;

import java.util.HashMap;
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

    public List<FlightDto> getDepartureFlightList() {
        Map<String, String> params = new HashMap<>();
        params.put("dep_iata", "TIA");
        FlightDataResponse departingFlights = airDataService.getFlights(params);
        return departingFlights.getData().stream()
                .map(airData -> parseToFlightDto(airData))
                .toList();
    }

    public List<FlightDto> getArrivingFlightList() {
        Map<String, String> params = new HashMap<>();
        params.put("arr_iata", "TAN");
        FlightDataResponse arrivingFlights = airDataService.getFlights(params);
        return arrivingFlights.getData().stream()
                .map(airData -> parseToFlightDto(airData))
                .toList();
        //TODO: Store data in DB
        //TODO: convert from AirData to FlightDto
    }

    public FlightDto parseToFlightDto(AirData airData) {
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
