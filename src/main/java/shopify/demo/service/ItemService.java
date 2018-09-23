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

    if (item.equals(null)) return item;

    else if(exists(item.getId())) return getItem(item.getId());

    return itemRepository.save(item);
  }

  public void delete(Long id) {
    itemRepository.deleteById(id);
  }

  public void deleteItem(Item item) {
    itemRepository.delete(item);
  }

  public Item editItem(Item item) {
    return itemRepository.save(item);
  }

  public boolean exists(Long id) {
    return itemRepository.existsById(id);
  }

  public Item getItem(Long id) {
    return itemRepository.findById(id).get();
  }
}
