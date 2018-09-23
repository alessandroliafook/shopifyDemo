package shopify.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopify.demo.model.item.LineItem;
import shopify.demo.model.order.Order;
import shopify.demo.model.shop.Shop;
import shopify.demo.repository.ShopRepository;

@Service
public class ShopService {

  @Autowired
  private ShopRepository shopRepository;

  @Autowired
  private LineItemService lineItemService;

  @Autowired
  private OrderService orderService;

  @Transactional
  public Shop create(Shop shop) {

    List<LineItem> products = shop.getProducts();
    products = lineItemService.saveLineItens(products);
    shop.setProducts(products);

    List<Order> orders = shop.getOrders();
    orders = orderService.createOrders(orders);
    shop.setOrders(orders);

    return shopRepository.save(shop);
  }

  @Transactional
  public void delete(String name) {

    Shop shop = shopRepository.findById(name).get();

    shopRepository.delete(shop);
    lineItemService.deleteLineItems(shop.getProducts());
    orderService.deleteOrders(shop.getOrders());

  }

  @Transactional
  public Shop editItem(Shop shop) {
    return shopRepository.save(shop);
  }

  public List<Shop> listAll() {
    return shopRepository.findAll();
  }

  public Shop get(String shopName) {
    return shopRepository.findById(shopName).get();
  }
}
