package agnieszka.rest.service;

import agnieszka.rest.dto.ProductDto;
import agnieszka.rest.entity.Product;
import agnieszka.rest.exception.ProductDoesNotExistException;
import agnieszka.rest.mapper.ProductMapper;
import agnieszka.rest.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto addProduct(ProductDto productDto) {
        if (productDto.getName().isBlank()) {
            throw new ProductDoesNotExistException("Product name cannot be empty!");
        }
        Product product = productMapper.toProductEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductDto(savedProduct);
    }

    public List<Product> getProducts(Integer maxPrice) {
        if (maxPrice == null) {
            return productRepository.findAll();
        }
        return productRepository.findByPriceLessThanEqual(maxPrice);
    }

    public void updateProduct(ProductDto productDto) {
        Product product = productMapper.toProductEntity(productDto);
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
        }
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
