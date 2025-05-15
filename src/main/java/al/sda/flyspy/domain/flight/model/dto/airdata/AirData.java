package al.sda.flyspy.domain.flight.model.dto.airdata;

public class AirData {
    private String flightDate;
    private String flightStatus;
    private TerminalPoint departure;
    private Airline airline;
    private FlightIdentifier flight;
    private Aircraft aircraft;
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
}
