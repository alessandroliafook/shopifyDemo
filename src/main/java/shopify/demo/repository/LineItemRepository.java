package shopify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopify.demo.model.item.Item;
import shopify.demo.model.item.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

  boolean existsByItemEquals(Item item);

}
