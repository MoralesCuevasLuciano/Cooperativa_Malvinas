package cooperativa_malvinas.models.exceptions;

public class AddressExistsException extends RuntimeException {
  public AddressExistsException(String message) {
    super(message);
  }
}
