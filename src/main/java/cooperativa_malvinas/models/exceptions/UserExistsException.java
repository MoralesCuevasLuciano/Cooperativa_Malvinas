package cooperativa_malvinas.models.exceptions;

public class UserExistsException extends RuntimeException {
  public UserExistsException(String message) {
    super(message);
  }
}
