package shopify.demo.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopify.demo.exceptions.InvalidLineItemException;
import shopify.demo.model.item.Product;
import shopify.demo.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public Product create(Product product) {

    if (product.equals(null)) throw new InvalidLineItemException();

    return productRepository.save(product);
  }

  public Product edit(Product product) {
    return productRepository.save(product);
  }

  public void delete(Product product) {

    productRepository.delete(product);
  }

  public void delete(List<Product> products) {

    for (Product product : products) {

      delete(product);
    }
  }

  public Product get(Long id) {
    return productRepository.findById(id).get();
  }

  public List<Product> delete(List<Product> lineItens, Product product) {

    List<Product> updatedLineItens = Arrays.asList();

    for (Product lineItem : lineItens) {

      if(lineItem.getName().equals(product.getName()))
        productRepository.delete(lineItem);

      else
        updatedLineItens.add(lineItem);
    }

    return updatedLineItens;
  }
}
