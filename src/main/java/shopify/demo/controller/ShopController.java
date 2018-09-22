package shopify.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import shopify.demo.service.ShopService;

@RestController
public class ShopController {

  @Autowired
  ShopService shopService;




}
