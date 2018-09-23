package shopify.demo.repository;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import shopify.demo.model.item.ProductTypeEnum;
import shopify.demo.model.item.Product;
import shopify.demo.model.order.Order;
import shopify.demo.model.shop.Shop;

@Component
public class RepositoryLoader implements ApplicationRunner {

  private final ShopRepository shopRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  @Autowired
  public RepositoryLoader(ShopRepository shopRepository,
      OrderRepository orderRepository,
      ProductRepository productRepository) {
    this.shopRepository = shopRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

      Product product = new Product();
      product.setName("Mouse");
      product.setPrice(10.50);
      product.setType(ProductTypeEnum.PRODUCT);
      product.setQuantity(10);

      product = productRepository.save(product);

      Product lineItem = new Product();
      lineItem.setName("Mouse");
      lineItem.setPrice(10.50);
      lineItem.setType(ProductTypeEnum.PRODUCT);
      lineItem.setQuantity(5);

      lineItem = productRepository.save(lineItem);

      Order order = new Order();
      order.setLineItens(Arrays.asList(lineItem));
      order.updateTotal();

      orderRepository.save(order);

      Shop infoShop = new Shop();
      infoShop.setName("Info Shop");
      infoShop.setProducts(Arrays.asList(product));
      infoShop.setOrders(Arrays.asList(order));

      shopRepository.save(infoShop);
    }

}
