package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
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

    public List<FlightDto> getFlightList() {
        Map<String, String> params = new HashMap<>();
        params.put("dep_iata", "TIA");
        FlightDataResponse departingFlights = airDataService.getFlights(params);
        params.clear();
        params.put("arr_iata", "TAN");
        FlightDataResponse arrivingFlights = airDataService.getFlights(params);

        //TODO: Store data in DB
        //TODO: convert from AirData to FlightDto
        return List.of();
    }
}
