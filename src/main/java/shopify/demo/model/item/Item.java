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

@Entity(name = "item_table")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Item name can not be null.")
  @NotEmpty(message = "Item name can not be empty.")
  @Column(unique = true)
  private String name;

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

  public double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ItemTypeEnum getType() {
    return type;
  }

  public void setType(ItemTypeEnum type) {
    this.type = type;
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
