package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateShopException extends RuntimeException {

  public DuplicateShopException() {
    super("Shop already exists in system.");
  }
}
