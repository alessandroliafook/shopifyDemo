package shopify.demo.model.shop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import shopify.demo.model.item.Product;
import shopify.demo.model.order.Order;

@Entity(name = "shop_table")
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, name = "shop_id")
  private Long id;

  @NotNull(message = "Shop name can not be null.")
  @NotEmpty(message = "Shop name can not be empty.")
  @Column(unique = true)
  private String name;

  @OneToMany
  private List<Product> products;

  @OneToMany
  private List<Order> orders;

  public Shop() {
  }

  public Shop(String name) {
    this.name = name;
    this.products = Arrays.asList();
    this.orders = Arrays.asList();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
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
    return Objects.equals(getId(), shop.getId()) &&
        Objects.equals(getName(), shop.getName());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), getName());
  }

  public Product getProduct(Product product) {

    Product storedProduct = null;

    Iterator<Product> storedProducts = getProducts().iterator();

    while (storedProducts.hasNext()) {

      storedProduct = storedProducts.next();

      if (storedProduct.getName().equals(product.getName())) break;

      storedProduct = null;
    }

    return storedProduct;
  }
}
