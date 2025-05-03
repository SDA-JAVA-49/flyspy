package al.sda.flyspy.domain.flight;

public enum FlightStatus {
    SCHEDULED,
    DELAYED,
    CANCELLED,
    IN_FLIGHT,
    LANDED,
    DIVERTED,
    UNKNOWN;

    public static FlightStatus fromString(String status) {
        try {
            return FlightStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
