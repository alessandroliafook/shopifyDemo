package shopify.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shopify.demo.model.item.Item;
import shopify.demo.service.ItemService;
import shopify.demo.service.ShopService;

@RestController
public class ShopController {

  @Autowired
  ItemService itemService;

  @PostMapping("/item")
  @ResponseBody
  public Item addItem(@RequestBody Item item) {
    return itemService.createItem(item);
  }




}
