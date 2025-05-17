package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightDataResponse;

import java.util.List;
import java.util.Map;

public interface AirDataService {
    public FlightDataResponse getFlights(Map<String, String> params);
}
