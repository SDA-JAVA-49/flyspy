package al.sda.flyspy.domain.flight.model;

import al.sda.flyspy.domain.airline.model.entity.Airline;
import al.sda.flyspy.domain.airport.model.entity.Airport;
import al.sda.flyspy.domain.flight.FlightStatus;
import al.sda.flyspy.domain.flight.model.entity.Flight;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

public class FlightService {
    final FlightRepository flightRepository;


    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    public void saveFlight(LocalDateTime flightDate, LocalDateTime arrivalDate, String flightNumber, FlightStatus flightStatus, Airport departure, Airport arrival, Airline operatingAirline) {
        Flight flight = new Flight();
        flight.setFlightDate(flightDate);
        flight.setArrivalDate(arrivalDate);
        flight.setFlightNumber(flightNumber);
        flight.setFlightStatus(flightStatus);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setOperatingAirline(operatingAirline);
        flightRepository.save(flight);
    }
    public java.util.Set<String> getAllFlights() {
        return flightRepository.getAll().stream()
                .map(Objects::toString)
                .collect(Collectors.toSet());
    }
}
