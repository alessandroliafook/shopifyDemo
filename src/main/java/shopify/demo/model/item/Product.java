package shopify.demo.model.item;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity(name = "product_table")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, name = "product_id")
  private Long id;

  @NotNull(message = "Item name can not be null.")
  @NotEmpty(message = "Item name can not be empty.")
  private String name;

  @DecimalMin(value = "0.1", message = "Item can not be priced lower than $0.1.")
  private double price;

  @NotNull(message = "Item must have a type.")
  private ProductTypeEnum type;

  @PositiveOrZero(message = "Stock can not have negative quantity.")
  private int quantity;

  public Product() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public ProductTypeEnum getType() {
    return type;
  }

  public void setType(ProductTypeEnum type) {
    this.type = type;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product)) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(getId(), product.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
