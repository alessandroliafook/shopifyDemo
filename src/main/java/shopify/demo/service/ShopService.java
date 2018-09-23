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
  public Shop create(String name) {

    Shop shop = new Shop(name);
    return shopRepository.save(shop);
  }

  @Transactional
  public void delete(String name) {

    Shop shop = shopRepository.findById(name).get();
    List<LineItem> products = shop.getProducts();
    List<Order> orders = shop.getOrders();

    shopRepository.delete(shop);
    lineItemService.deleteLineItems(products);
    orderService.deleteOrders(orders);
  }

  @Transactional
  public Shop editItem(String oldName, String newName) {

    Shop shop = shopRepository.getOne(oldName);

    if(shop.equals(null)) return shop;

    shop.setName(newName);

    return shopRepository.save(shop);
  }

  public List<Shop> listAll() {
    return shopRepository.findAll();
  }

  public Shop get(String shopName) {
    return shopRepository.findById(shopName).get();
  }
}
