package shopify.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shopify.demo.model.shop.Shop;
import shopify.demo.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {

  @Autowired
  ShopService shopService;

  @PostMapping("/{name}")
  @ResponseBody
  public Shop register(@PathVariable String name) {
    return shopService.create(name);
  }

  @GetMapping
  @ResponseBody
  public List<Shop> listAll() {
    return shopService.listAll();
  }

  @DeleteMapping("/{name}")
  public void delete(@PathVariable String name) {
    shopService.delete(name);
  }

  @GetMapping("/{name}")
  @ResponseBody
  public Shop get(@PathVariable String name) {
    return shopService.get(name);
  }

  @PatchMapping("/{oldName}/{newName}")
  @ResponseBody
  public Shop updateName(@PathVariable String oldName, @PathVariable String newName) {
    return shopService.editItem(oldName, newName);
  }
}
