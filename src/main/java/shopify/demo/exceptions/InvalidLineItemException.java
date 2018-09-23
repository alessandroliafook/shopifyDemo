package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLineItemException extends RuntimeException {

  public InvalidLineItemException() {
    super("Line Item can not be null!");
  }
}
