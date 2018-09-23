package shopify.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.model.item.Item;
import shopify.demo.model.shop.Shop;
import shopify.demo.repository.ShopRepository;

@Service
public class ShopService {

  @Autowired
  private ShopRepository shopRepository;

  public Shop createItem(Shop shop) {
    return shopRepository.save(shop);
  }

  public void deleteItem(Long id) {
    shopRepository.deleteById(id);
  }

  public Shop editItem(Shop shop) {
    return shopRepository.save(shop);
  }

}
