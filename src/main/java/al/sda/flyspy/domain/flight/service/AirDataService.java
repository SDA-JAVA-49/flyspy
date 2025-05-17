package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface AirDataService {
    List<AirData> getFlights(Map<String, String> params) throws URISyntaxException;
}
