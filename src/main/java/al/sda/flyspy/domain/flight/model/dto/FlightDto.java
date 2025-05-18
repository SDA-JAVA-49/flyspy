package al.sda.flyspy.domain.flight.model.dto;

import java.util.Objects;

public class FlightDto {
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String aircraftRegistration;
    private String airlineName;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAircraftRegistration() {
        return aircraftRegistration;
    }

    public void setAircraftRegistration(String aircraftRegistration) {
        this.aircraftRegistration = aircraftRegistration;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDto flightDto = (FlightDto) o;
        return Objects.equals(flightNumber, flightDto.flightNumber)
                && Objects.equals(departureAirport, flightDto.departureAirport)
                && Objects.equals(this.arrivalAirport, flightDto.getArrivalAirport())
                && Objects.equals(this.departureTime, flightDto.getDepartureTime())
                && Objects.equals(this.arrivalTime, flightDto.getArrivalTime())
                && Objects.equals(this.aircraftRegistration, flightDto.getAircraftRegistration())
                && Objects.equals(this.airlineName, flightDto.getAirlineName());
    }

}
