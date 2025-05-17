package al.sda.flyspy.shared.exceptions;

public class CommandNotFoundException extends RuntimeException {
  public CommandNotFoundException(String message) {
    super(message);
  }
}
