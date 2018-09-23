package shopify.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopify.demo.exceptions.DuplicateShopException;
import shopify.demo.exceptions.InvalidShopException;
import shopify.demo.exceptions.NotRegistredLineItemException;
import shopify.demo.exceptions.ProductDuplicateException;
import shopify.demo.model.item.Product;
import shopify.demo.model.order.Order;
import shopify.demo.model.shop.Shop;
import shopify.demo.repository.ShopRepository;

@Service
public class ShopService {

  @Autowired
  private ShopRepository shopRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private OrderService orderService;

  @Transactional
  public Shop create(String name) {

    Shop shop = new Shop(name);
    return shopRepository.save(shop);
  }

  @Transactional
  public void delete(String name) {

    Shop shop = shopRepository.findByName(name);
    List<Product> products = shop.getProducts();
    List<Order> orders = shop.getOrders();

    shopRepository.delete(shop);
    productService.delete(products);
    orderService.deleteOrders(orders);
  }

  @Transactional
  public Shop updateShopName(String oldName, String newName) {

    Shop shop = shopRepository.findByName(oldName);

    if(shop.equals(null)) throw new InvalidShopException();
    else if (shopRepository.existsByName(newName)) throw new DuplicateShopException();

    shop.setName(newName);

    return shopRepository.save(shop);
  }

  public List<Shop> listAll() {
    return shopRepository.findAll();
  }

  public Shop get(String shopName) {
    return shopRepository.findByName(shopName);
  }

  @Transactional
  public Shop addProduct(String shopId, Product product) {

    Shop shop = shopRepository.findByName(shopId);

    if (shop.contains(product)) throw new ProductDuplicateException();

    Product savedProduct = productService.create(product);

    shop.getProducts().add(savedProduct);

    return shopRepository.save(shop);
  }

  public List<Product> listAllProducts(String shopName) {
    return shopRepository.findByName(shopName).getProducts();
  }

  @Transactional
  public void deleteProduct(String shopName, Long id) {

    Shop shop = shopRepository.findByName(shopName);
    Product product = productService.get(id);

    shop.getProducts().remove(product);
    orderService.deleteProduct(shop.getOrders(), product);
    shopRepository.save(shop);

    productService.delete(product);
  }

  public Shop updateProduct(String shopName, Product product) {

    Shop shop = shopRepository.findByName(shopName);

    if (!shop.getProducts().contains(product)) throw new NotRegistredLineItemException();

    Product savedProduct = productService.edit(product);

    return shop;
  }

  public Product getProduct(String shopName, Long id) {

    Product product = productService.get(id);
    Shop shop = shopRepository.findByName(shopName);

    if (shop.getProducts().contains(product)) return product;
    else throw new NotRegistredLineItemException();
  }
}
