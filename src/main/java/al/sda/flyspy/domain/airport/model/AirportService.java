package al.sda.flyspy.domain.airport.model;

import al.sda.flyspy.domain.airport.model.entity.Airport;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import org.hibernate.mapping.Set;

import java.util.Objects;
import java.util.stream.Collectors;

public class AirportService {
    final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
    public void saveAirport(String iataCode, String icaoCode, String name, String country, String city, Set arrivals, Set departures) {
        Airport airport = new Airport();
        airport.setIataCode(iataCode);
        airport.setIcaoCode(icaoCode);
        airport.setName(name);
        airport.setCountry(country);
        airport.setCity(city);
        airport.setArrivals((java.util.Set<Flight>) arrivals);
        airport.setDepartures((java.util.Set<Flight>) departures);
        airportRepository.save(airport);
    }
    public java.util.Set<String> getAllAirports() {
        return airportRepository.getAll().stream()
                .map(Objects::toString)
                .collect(Collectors.toSet());
    }
}
