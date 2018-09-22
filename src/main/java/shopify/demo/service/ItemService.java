package shopify.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.model.item.Item;
import shopify.demo.repository.ItemRepository;

@Service
public class ItemService {

  @Autowired
  ItemRepository itemRepository;

  public Item createItem(Item item) {
    return itemRepository.save(item);
  }

  public void deleteItem(Long id) {
    itemRepository.deleteById(id);
  }

  public Item editItem(Item item) {
    return itemRepository.save(item);
  }

}
