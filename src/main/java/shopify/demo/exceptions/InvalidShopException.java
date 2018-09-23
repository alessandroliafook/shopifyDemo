package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidShopException extends RuntimeException {

  public InvalidShopException() {
    super("Shop can not be null!");
  }
}
