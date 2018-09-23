package shopify.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.model.item.Item;
import shopify.demo.model.item.LineItem;
import shopify.demo.repository.LineItemRepository;

@Service
public class LineItemService {

  @Autowired
  private LineItemRepository lineItemRepository;

  @Autowired
  private ItemService itemService;

  public LineItem createLineItem(LineItem lineItem) {

    if (lineItem.equals(null)) return lineItem;

    else if (exists(lineItem)) return lineItemRepository.findById(lineItem.getId()).get();

    Item item = itemService.createItem(lineItem.getItem());
    lineItem.setItem(item);

    return lineItemRepository.save(lineItem);
  }

  private boolean exists(LineItem lineItem) {
    return lineItemRepository.existsById(lineItem.getId());
  }


  public LineItem editItem(LineItem lineItem) {
    return lineItemRepository.save(lineItem);
  }

  public void deleteItem(Long id) {
    lineItemRepository.deleteById(id);

  }

  public void deleteLineItem(LineItem lineItem) {

    lineItemRepository.delete(lineItem);

    Item item = lineItem.getItem();

    if (itemService.exists(item.getId()) &&
        lineItemRepository.existsByItemEquals(item))
      itemService.deleteItem(item);
  }

  public List<LineItem> saveLineItens(List<LineItem> lineItems) {

    if(lineItems.equals(null)) return lineItems;

    List<LineItem> newLineItens = new ArrayList<>();

    for (LineItem lineItem : lineItems) {

      LineItem newLineItem = createLineItem(lineItem);
      if (newLineItem.equals(null)) newLineItens.add(newLineItem);
    }

    return newLineItens;
  }

  public void deleteLineItems(List<LineItem> lineItems) {

    for (LineItem product : lineItems) {

      deleteLineItem(product);
    }
  }
}
