package shopify.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shopify.demo.model.item.Product;
import shopify.demo.model.shop.Shop;
import shopify.demo.service.ShopService;

@RestController
@RequestMapping("/product/{shopName}")
public class ProductController {

  @Autowired
  private ShopService shopService;

  @PostMapping
  @ResponseBody
  public Shop registerProduct(@PathVariable String shopName, @RequestBody Product product) {
    return shopService.addProduct(shopName, product);
  }

  @GetMapping
  @ResponseBody
  public List<Product> listAll(@PathVariable String shopName) {
    return shopService.listAllProducts(shopName);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String shopName, @PathVariable Long id) {
    shopService.deleteProduct(shopName, id);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Product get(@PathVariable String shopName, @PathVariable Long id) {
    return shopService.getProduct(shopName, id);
  }

  @PutMapping
  @ResponseBody
  public Shop updateProduct(@PathVariable String shopName, @RequestBody Product product) {
    return shopService.updateProduct(shopName, product);
  }
}
