package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightDataResponse;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.util.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightService {
    private final Repository<Flight, Long> repository;
    private final AirDataService airDataService;

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

    public FlightDto parseToFlightDto(AirData airData){
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber(airData.getFlight().getNumber());
        flightDto.setDepartureAirport(airData.getDeparture().getIata());
        flightDto.setArrivalAirport(airData.getArrival().getIata());
        flightDto.setDepartureTime(airData.getDeparture().getScheduled());
        flightDto.setArrivalTime(airData.getArrival().getScheduled());
        flightDto.setAirlineName(airData.getAirline().getName());

        if(airData.getAircraft() != null){
            flightDto.setAircraftRegistration(airData.getAircraft().getRegistration());}
        else{
            flightDto.setAircraftRegistration("⚠ Missing aircraft");
        }
        flightDto.setAirlineName(airData.getAirline().getName());


        return flightDto;
    }
}
