package shopify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopify.demo.model.item.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
