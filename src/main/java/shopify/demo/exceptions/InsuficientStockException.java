package shopify.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsuficientStockException extends RuntimeException {

  public InsuficientStockException(int quantity) {
    super("Do not have enough products in stock for the acquisition.\n"
        + "Actual quantity is: " + quantity);
  }
}
