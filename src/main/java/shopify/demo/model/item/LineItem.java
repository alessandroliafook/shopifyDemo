package shopify.demo.model.item;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

@Entity(name = "line_item_table")
public class LineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, name = "line_item_id")
  private Long id;

  @Min(value = 0, message = "Stock can not have negative quantity.")
  private int quantity;

  @OneToOne
  private Item item;

  public LineItem() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LineItem)) {
      return false;
    }
    LineItem lineItem = (LineItem) o;
    return Objects.equals(getId(), lineItem.getId());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId());
  }
}
