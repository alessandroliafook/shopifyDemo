package shopify.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.model.order.Order;
import shopify.demo.repository.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  public void deleteOrder(Long id) {
    orderRepository.deleteById(id);
  }

  public Order editOrder(Order order) {
    return orderRepository.save(order);
  }

}
