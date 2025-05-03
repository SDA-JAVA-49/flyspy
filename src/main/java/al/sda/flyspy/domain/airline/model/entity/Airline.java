package al.sda.flyspy.domain.airline.model.entity;

import al.sda.flyspy.domain.flight.model.entity.Flight;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "airlines")
public class Airline {
    @Id
    @Column(name = "iata_code", length = 5)
    private String iataCode;
    @Column(name = "icao_code", length = 6, nullable = false, unique = true)
    private String icaoCode;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "operatingAirline")
    private Set<Flight> flights;

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
