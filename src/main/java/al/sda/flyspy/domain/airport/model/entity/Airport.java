package al.sda.flyspy.domain.airport.model.entity;

import al.sda.flyspy.domain.flight.model.entity.Flight;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @Column(name = "iata_code", length = 5)
    private String iataCode;
    @Column(name = "icao_code", length = 6, nullable = false, unique = true)
    private String icaoCode;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "country", nullable = false)
    private String country;
    @OneToMany(mappedBy = "departure")
    private Set<Flight> departures;
    @OneToMany(mappedBy = "arrival", cascade = CascadeType.ALL)
    private Set<Flight> arrivals;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(Set<Flight> departures) {
        this.departures = departures;
    }

    public Set<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(Set<Flight> arrivals) {
        this.arrivals = arrivals;
    }
}
