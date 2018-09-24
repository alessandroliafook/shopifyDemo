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
import shopify.demo.model.item.Product;
import shopify.demo.model.order.Order;
import shopify.demo.model.shop.Shop;
import shopify.demo.service.OrderService;
import shopify.demo.service.ShopService;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private ShopService shopService;

  @Autowired
  private OrderService orderService;

  @PostMapping("/{shopName}")
  @ResponseBody
  public Shop register(@PathVariable String shopName) {
    return shopService.createOrder(shopName);
  }

  @GetMapping("/shop/{shopName}")
  @ResponseBody
  public List<Order> listAllByShop(@PathVariable String shopName) {
    return shopService.listAllOrders(shopName);
  }

  @GetMapping
  @ResponseBody
  public List<Order> listAll() {
    List<Order> orders = orderService.listAll();
    return orders;
  }

  @DeleteMapping("/{shopName}/{order_id}")
  public void delete(@PathVariable String shopName, @PathVariable Long order_id) {
    shopService.deleteOrder(shopName, order_id);
  }

  @GetMapping("/{order_id}")
  @ResponseBody
  public Order get(@PathVariable Long order_id) {
    return orderService.get(order_id);
  }

  @PatchMapping("/{shopName}/{order_id}")
  @ResponseBody
  public Order addLineItem(@PathVariable String shopName, @PathVariable Long order_id,
      @RequestBody Product lineItem) {
    return shopService.addLineItemToOrder(shopName, order_id, lineItem);
  }

  @PatchMapping("/{shopName}/{order_id}/{line_item_id}")
  @ResponseBody
  public Order removeLineItem(@PathVariable String shopName, @PathVariable Long order_id,
      @PathVariable Long line_item_id) {
    return shopService.removeLineItemToOrder(shopName, order_id, line_item_id);
  }

  @PatchMapping("/{shopName}/{order_id}/{line_item_id}/{quantity}")
  @ResponseBody
  public Order editLineItemQuantity(@PathVariable String shopName, @PathVariable Long order_id,
      @PathVariable Long line_item_id, @PathVariable Integer quantity) {
    return shopService.editLineItemQuantityToOrder(shopName, order_id, line_item_id, quantity);
  }
}