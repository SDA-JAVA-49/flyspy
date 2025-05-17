package al.sda.flyspy.domain.flight.model.dto.airdata;

import org.codehaus.jackson.annotate.JsonProperty;


public class AirData {
    @JsonProperty("flight_date")
    private String flightDate;
    @JsonProperty("flight_status")
    private String flightStatus;
    private TerminalPoint departure;
    private TerminalPoint arrival;
    @JsonProperty("airline")
    private Airline airline;
    @JsonProperty("flight")
    private FlightIdentifier flight;
    @JsonProperty("aircraft")
    private Aircraft aircraft;
    @JsonProperty("live")
    private LiveData live;

    // Getters and Setters
    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public TerminalPoint getDeparture() {
        return departure;
    }

    public void setDeparture(TerminalPoint departure) {
        this.departure = departure;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public FlightIdentifier getFlight() {
        return flight;
    }

    public void setFlight(FlightIdentifier flight) {
        this.flight = flight;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public LiveData getLive() {
        return live;
    }

    public void setLive(LiveData live) {
        this.live = live;
    }

    public TerminalPoint getArrival() {
        return arrival;
    }

    public void setArrival(TerminalPoint arrival) {
        this.arrival = arrival;
    }
}
