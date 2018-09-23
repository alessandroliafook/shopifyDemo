package shopify.demo.model.shop;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import shopify.demo.model.item.LineItem;
import shopify.demo.model.order.Order;

@Entity(name = "shop_table")
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "shop_id")
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  private List<LineItem> lineItems;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Order> orders;

  public Shop() {
  }

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

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Shop)) {
      return false;
    }
    Shop shop = (Shop) o;
    return Objects.equals(getId(), shop.getId());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId());
  }
}
