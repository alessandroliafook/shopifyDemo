package shopify.demo.model.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import shopify.demo.model.item.LineItem;
import shopify.demo.model.order.Order;

@Entity(name = "shop_table")
public class Shop {

  @Id
  @NotNull(message = "Shop name can not be null.")
  @NotEmpty(message = "Shop name can not be empty.")
  @Column(unique = true)
  private String name;

  @OneToMany
  private List<LineItem> products;

  @OneToMany
  private List<Order> orders;

  public Shop() {
  }

  public Shop(String name) {
    this.name = name;
    this.products = Arrays.asList();
    this.orders = Arrays.asList();
  }

  public List<LineItem> getProducts() {
    return products;
  }

  public void setProducts(List<LineItem> products) {
    this.products = products;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    return Objects.equals(getName(), shop.getName());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getName());
  }
}
