package shopify.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopify.demo.exceptions.DuplicateShopException;
import shopify.demo.exceptions.InsuficientStockException;
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

    if (shop.equals(null)) {
      throw new InvalidShopException();
    } else if (shopRepository.existsByName(newName)) {
      throw new DuplicateShopException();
    }

    shop.setName(newName);

    return shopRepository.save(shop);
  }

  public List<Shop> listAll() {
    return shopRepository.findAll();
  }

  public Shop get(String shopName) {
    return shopRepository.findByName(shopName);
  }

  // Product Methods

  @Transactional
  public Shop addProduct(String shopId, Product product) {

    Shop shop = shopRepository.findByName(shopId);
    Product storedProduct = shop.getProduct(product);

    if (storedProduct != null) {
      throw new ProductDuplicateException();
    }

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

  @Transactional
  public Shop updateProduct(String shopName, Product product) {

    Shop shop = shopRepository.findByName(shopName);

    if (!shop.getProducts().contains(product)) {
      throw new NotRegistredLineItemException();
    }

    productService.update(product);

    return shop;
  }

  public Product getProduct(String shopName, Long id) {

    Product product = productService.get(id);
    Shop shop = shopRepository.findByName(shopName);

    if (shop.getProducts().contains(product)) {
      return product;
    } else {
      throw new NotRegistredLineItemException();
    }
  }

  // Order Methods

  @Transactional
  public Shop createOrder(String shopName) {

    Order order = orderService.create();

    Shop shop = shopRepository.findByName(shopName);
    shop.getOrders().add(order);

    return shopRepository.save(shop);
  }

  public List<Order> listAllOrders(String shopName) {
    return shopRepository.findByName(shopName).getOrders();
  }

  @Transactional
  public Order addLineItemToOrder(String shopName, Long order_id, Product lineItem) {

    Shop shop = shopRepository.findByName(shopName);
    Product storedProduct = shop.getProduct(lineItem);

    checkStockQuantity(lineItem.getQuantity(), storedProduct);

    updateProductQuantity((-1) * lineItem.getQuantity(), storedProduct);

    Product storedLineItem = productService.create(lineItem);
    Order order = orderService.get(order_id);
    order.getLineItens().add(storedLineItem);
    order.updateTotal();

    return orderService.update(order);
  }

  @Transactional
  public Order removeLineItemToOrder(String shopName, Long order_id, Long line_item_id) {

    Shop shop = shopRepository.findByName(shopName);
    Product lineItem = productService.get(line_item_id);
    Product storedProduct = shop.getProduct(lineItem);

    updateProductQuantity(lineItem.getQuantity(), storedProduct);

    return orderService.deleteLineItem(order_id, lineItem);
  }

  public Order editLineItemQuantityToOrder(String shopName, Long order_id, Long line_item_id,
      Integer quantity) {

    Product lineItem = productService.get(line_item_id);
    Shop shop = shopRepository.findByName(shopName);
    Product storedProduct = shop.getProduct(lineItem);

    checkStockQuantity(quantity, storedProduct);
    updateProductQuantity((-1) * quantity, storedProduct);
    updateProductQuantity(quantity, lineItem);

    Order order = orderService.get(order_id);
    order.updateTotal();

    return orderService.update(order);
  }

  @Transactional
  public void deleteOrder(String shopName, Long order_id) {

    Shop shop = shopRepository.findByName(shopName);
    Order order = orderService.get(order_id);

    shop.getOrders().remove(order);

    for (Product lineItem : order.getLineItens()) {

      Product storedProduct = shop.getProduct(lineItem);
      updateProductQuantity(lineItem.getQuantity() , storedProduct);
    }

    shopRepository.save(shop);
    orderService.deleteOrder(order);
  }

  private void updateProductQuantity(Integer quantity, Product storedProduct) {
    int newTotalOfProducts = storedProduct.getQuantity() + quantity;
    storedProduct.setQuantity(newTotalOfProducts);
    productService.update(storedProduct);
  }

  private void checkStockQuantity(Integer quantity, Product storedProduct) {
    if (storedProduct.equals(null)) {
      throw new NotRegistredLineItemException();
    } else if (storedProduct.getQuantity() < quantity ||
        storedProduct.getQuantity() == 0) {
      throw new InsuficientStockException(storedProduct.getQuantity());
    }
  }
}
