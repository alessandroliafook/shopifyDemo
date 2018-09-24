package shopify.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopify.demo.model.item.Product;
import shopify.demo.model.order.Order;
import shopify.demo.repository.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductService productService;

  public Order create() {

    Order order = new Order();
    return orderRepository.save(order);
  }

  @Transactional
  public void delete(Long id) {

    Order order = orderRepository.findById(id).get();

    orderRepository.deleteById(id);

    productService.delete(order.getLineItens());
  }

  public void deleteOrder(Order order) {
    orderRepository.delete(order);
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

  public List<Order> listAll() {
    return orderRepository.findAll();
  }

  public Order get(Long order_id) {
    return orderRepository.findById(order_id).get();
  }

  public Order update(Order order) {
    return orderRepository.save(order);
  }

  public Order deleteLineItem(Long order_id, Product lineItem) {

    Order order = orderRepository.findById(order_id).get();

    productService.delete(lineItem);
    order.getLineItens().remove(lineItem);
    order.updateTotal();

    return orderRepository.save(order);
  }
}
