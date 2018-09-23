package shopify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopify.demo.model.shop.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

  Shop findByName(String name);

  boolean existsByName(String name);

}
