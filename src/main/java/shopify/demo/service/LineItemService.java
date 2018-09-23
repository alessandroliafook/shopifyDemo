package shopify.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.model.item.LineItem;
import shopify.demo.repository.LineItemRepository;

@Service
public class LineItemService {

  @Autowired
  private LineItemRepository lineItemRepository;

  public LineItem createItem(LineItem lineItem) {
    return lineItemRepository.save(lineItem);
  }

  public void deleteItem(Long id) {
    lineItemRepository.deleteById(id);
  }

  public LineItem editItem(LineItem lineItem) {
    return lineItemRepository.save(lineItem);
  }

}
