package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateLineItemException extends RuntimeException {

  public DuplicateLineItemException() {
    super("Already exists this Line Item in the shop.");
  }
}
