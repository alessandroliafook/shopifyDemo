package shopify.demo.repository;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import shopify.demo.model.item.Item;
import shopify.demo.model.item.ItemTypeEnum;
import shopify.demo.model.item.LineItem;
import shopify.demo.model.order.Order;
import shopify.demo.model.shop.Shop;

@Component
public class RepositoryLoader implements ApplicationRunner {

  private final ShopRepository shopRepository;
  private final OrderRepository orderRepository;
  private final LineItemRepository lineItemRepository;
  private final ItemRepository itemRepository;

  @Autowired
  public RepositoryLoader(ShopRepository shopRepository,
      OrderRepository orderRepository,
      LineItemRepository lineItemRepository, ItemRepository itemRepository) {
    this.shopRepository = shopRepository;
    this.orderRepository = orderRepository;
    this.lineItemRepository = lineItemRepository;
    this.itemRepository = itemRepository;
  }


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

      Item mouse = new Item();
      mouse.setName("Mouse");
      mouse.setPrice(10.50);
      mouse.setType(ItemTypeEnum.PRODUCT);

      mouse = itemRepository.save(mouse);

      LineItem productsLine = new LineItem();
      productsLine.setItem(mouse);
      productsLine.setQuantity(10);

      productsLine = lineItemRepository.save(productsLine);

      LineItem orderLine = new LineItem();
      orderLine.setItem(mouse);
      orderLine.setQuantity(5);

      orderLine = lineItemRepository.save(orderLine);

      Order order = new Order();
      order.setLineItems(Arrays.asList(orderLine));
      order.updateTotal();

      orderRepository.save(order);

      Shop infoShop = new Shop();
      infoShop.setName("Info Shop");
      infoShop.setProducts(Arrays.asList(productsLine));
      infoShop.setOrders(Arrays.asList(order));

      shopRepository.save(infoShop);

    }

}
