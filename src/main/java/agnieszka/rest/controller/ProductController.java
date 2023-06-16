package agnieszka.rest.controller;

import agnieszka.rest.dto.ProductDto;
import agnieszka.rest.entity.Product;
import agnieszka.rest.exception.ProductDoesNotExistException;
import agnieszka.rest.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<Product> getAllProducts(Integer maxPrice) {
        return productService.getProducts(maxPrice);
    }

    @PostMapping("/api/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        try {
            ProductDto savedProduct = productService.addProduct(productDto);
            return ResponseEntity.created(URI.create("api/products/" + savedProduct.getId()))
                    .body(savedProduct);
        } catch (ProductDoesNotExistException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/api/products/{id}")
    public void updateProduct(@RequestBody ProductDto productDto, @PathVariable long id) {
        productDto.setId(id);
        productService.updateProduct(productDto);
    }

    @DeleteMapping("/api/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
