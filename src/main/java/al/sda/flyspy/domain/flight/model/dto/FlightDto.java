package al.sda.flyspy.domain.flight.model.dto;

import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;

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
    public FlightDto parseToFlightDto(AirData airData){
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber(airData.getFlight().getNumber());
        flightDto.setDepartureAirport(airData.getDeparture().getIata());
        flightDto.setArrivalAirport(airData.getArrival().getIata());
        flightDto.setDepartureTime(airData.getDeparture().getScheduled());
        flightDto.setArrivalTime(airData.getArrival().getScheduled());
        flightDto.setAircraftRegistration(airData.getAircraft().getRegistration());
        flightDto.setAirlineName(airData.getAirline().getName());


        return flightDto;
    }
}
