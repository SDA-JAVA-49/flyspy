package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.Repository;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlightService {
    private final Repository<Flight, Long> repository;
    private final AirDataService airDataService;

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

    private FlightDto convertToFlightDto(AirData airData) {
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber(airData.getFlight().getNumber());
        // flightDto.setAircraftRegistration(airData.getAircraft().getRegistration());
        flightDto.setAirlineName(airData.getAirline().getName());
        flightDto.setDepartureAirport(airData.getDeparture().getAirport());
        flightDto.setArrivalAirport(airData.getArrival().getAirport());
        flightDto.setDepartureTime(airData.getDeparture().getEstimated());
        flightDto.setArrivalTime(airData.getArrival().getEstimated());
        return flightDto;
    }
}

