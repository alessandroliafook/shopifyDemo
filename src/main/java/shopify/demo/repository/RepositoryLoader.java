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

  public RepositoryLoader(ShopRepository shopRepository) {
    this.shopRepository = shopRepository;
  }

  @Autowired

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

      Item mouse = new Item();
      mouse.setName("Mouse");
      mouse.setPrice(10.50);
      mouse.setType(ItemTypeEnum.PRODUCT);

      LineItem productsLine = new LineItem();
      productsLine.setItem(mouse);
      productsLine.setQuantity(10);

      LineItem orderLine = new LineItem();
      orderLine.setItem(mouse);
      orderLine.setQuantity(5);

      Order order = new Order();
      order.setLineItems(Arrays.asList(orderLine));
      order.updateTotal();

      Shop demoShop = new Shop();
      demoShop.setLineItems(Arrays.asList(productsLine));
      demoShop.setOrders(Arrays.asList(order));

      shopRepository.save(demoShop);

    }

}
