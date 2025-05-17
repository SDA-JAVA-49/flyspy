package al.sda.flyspy.domain.airline.model;

import al.sda.flyspy.domain.airline.model.entity.Airline;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AirlineService {
    final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
    public Set<String> getAllAirlines() {
        return airlineRepository.getAll().stream()
                .map(Objects::toString)
                .collect(Collectors.toSet());
    }
    public Airline saveAirline(String iataCode, String icaoCode, String name) {
        Airline airline = new Airline();
        airline.setIataCode(iataCode);
        airline.setIcaoCode(icaoCode);
        airline.setName(name);
        return airlineRepository.save(airline);
    }

}

