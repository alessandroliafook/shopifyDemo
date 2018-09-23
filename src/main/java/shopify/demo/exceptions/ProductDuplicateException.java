package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductDuplicateException extends RuntimeException {

  public ProductDuplicateException() {
    super("Product already exists in the shop.");
  }
}
