package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.Repository;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class FlightService {
    private final Repository<Flight, Long> repository;
    private final AirDataService airDataService;

    public FlightService(Repository<Flight, Long> repository, AirDataService airDataService) {
        this.repository = repository;
        this.airDataService = airDataService;
    }

    public List<FlightDto> getFlightList() throws URISyntaxException {
        List<AirData> departingFlights = airDataService.getFlights(Map.of("dep_iata", "TIA"));
        List<AirData> arrivingFlights = airDataService.getFlights(Map.of("arr_iata", "TIA"));
        //TODO: Store data in DB
        //TODO: convert from AirData to FlightDto
        return List.of();
    }
}
