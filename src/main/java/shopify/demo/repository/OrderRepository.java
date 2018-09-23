package shopify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopify.demo.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
