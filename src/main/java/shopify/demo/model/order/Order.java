package shopify.demo.model.order;


import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import shopify.demo.model.item.LineItem;

@Entity(name = "order_table")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, name = "order_id")
  private Long id;

  @OneToMany
  List<LineItem> lineItems;

  @DecimalMin(value = "0.0", message = "Total of the order can not be negative.")
  private double total;

  public Order() { }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<LineItem> getLineItems() {
    return lineItems;
  }

  public void setLineItems(List<LineItem> lineItems) {
    this.lineItems = lineItems;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void updateTotal() {
    this.total = 0.0;
    for (LineItem item : lineItems) {
      this.total += item.getItem().getPrice();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Order)) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(getId(), order.getId());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId());
  }
}
