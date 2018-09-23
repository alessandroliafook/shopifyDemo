package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotRegistredLineItemException extends RuntimeException{

  public NotRegistredLineItemException() {
    super("Line item not registred in shop!");
  }
}
