package shopify.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.model.item.LineItem;
import shopify.demo.model.order.Order;
import shopify.demo.repository.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private LineItemService lineItemService;

  public Order createOrder(Order order) {

    if (order.equals(null)) return order;

    else if (orderRepository.existsById(order.getId()))
      return orderRepository.findById(order.getId()).get();

    List<LineItem> lineItems = order.getLineItems();
    lineItems = lineItemService.saveLineItens(lineItems);
    order.setLineItems(lineItems);
    order.updateTotal();

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
      lineItemService.deleteLineItems(order.getLineItems());
    }
  }
}
