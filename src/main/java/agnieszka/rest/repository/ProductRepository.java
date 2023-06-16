package agnieszka.rest.repository;

import agnieszka.rest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceLessThanEqual(Integer maxPrice);
}
