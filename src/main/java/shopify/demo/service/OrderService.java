package shopify.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.model.item.Product;
import shopify.demo.model.order.Order;
import shopify.demo.repository.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductService productService;

  public Order createOrder(Order order) {

    if (order.equals(null)) return order;

    else if (orderRepository.existsById(order.getId()))
      return orderRepository.findById(order.getId()).get();

//    List<Product> lineItems = order.getLineItens();
//    lineItems = lineItemService.create(lineItems);
//    order.setLineItens(lineItems);
//    order.updateTotal();

    return orderRepository.save(order);
  }

  public void delete(Long id) {
    orderRepository.deleteById(id);
  }

  public void deleteOrder(Order order) {
    orderRepository.delete(order);
  }

  public Order editOrder(Order order) {
    return orderRepository.save(order);
  }

  public List<Order> createOrders(List<Order> orders) {

    List<Order> savedOrders = new ArrayList<>();

    for (Order order : orders) {

      Order newOrder = createOrder(order);
      if (!newOrder.equals(null)) savedOrders.add(newOrder);
    }

    return savedOrders;
  }

  public void deleteOrders(List<Order> orders) {

    for (Order order : orders) {

      deleteOrder(order);
      productService.delete(order.getLineItens());
    }
  }

  public void deleteProduct(List<Order> orders, Product product) {

    for (Order order : orders) {

      List<Product> updatedLineItens = productService.delete(order.getLineItens(), product);
      order.setLineItens(updatedLineItens);

      double total = order.getTotal();
      order.updateTotal();
      double newTotal = order.getTotal();

      if (newTotal != total) orderRepository.save(order);
    }
  }
}
