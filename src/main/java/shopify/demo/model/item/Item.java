package shopify.demo.model.item;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  @NotNull(message = "Item name can not be null.")
  @NotEmpty(message = "Item name can not be empty.")
  private String name;

  @Min(value = 0, message = "Stock can not have negative quantity.")
  private int quantity;

  @DecimalMin(value = "0.1", message = "Item can not be priced lower than $0.1.")
  private double price;

  @NotNull(message = "Item must have a type.")
  private ItemTypeEnum type;

  public Item() {
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(getId(), item.getId()) &&
        Objects.equals(getName(), item.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
