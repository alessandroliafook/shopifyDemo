package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidItemException extends RuntimeException {

  public InvalidItemException() {
    super("Item can not be null!");
  }
}
