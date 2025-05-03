package al.sda.flyspy.domain.flight.model.entity;

import al.sda.flyspy.domain.airline.model.entity.Airline;
import al.sda.flyspy.domain.airport.model.entity.Airport;
import al.sda.flyspy.domain.flight.FlightStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "departure_date_time", nullable = false)
    private LocalDateTime flightDate;
    @Column(name = "arrival_date_time", nullable = false)
    private LocalDateTime arrivalDate;
    @Column(name = "flight_number", length = 10, nullable = false)
    private String flightNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status", nullable = false)
    private FlightStatus flightStatus;
    @ManyToOne
    private Airport departure;
    @ManyToOne
    private Airport arrival;
    @ManyToOne
    private Airline operatingAirline;

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public Airline getOperatingAirline() {
        return operatingAirline;
    }

    public void setOperatingAirline(Airline operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    public LocalDateTime getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDateTime flightDate) {
        this.flightDate = flightDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}