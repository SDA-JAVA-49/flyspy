package al.sda.flyspy.shared.fault;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException() {
        super("There was an error while calling external service");
    }
}
