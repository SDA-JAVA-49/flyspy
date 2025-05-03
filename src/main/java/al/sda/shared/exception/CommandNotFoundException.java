package al.sda.shared.exception;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
