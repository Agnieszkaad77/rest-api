package agnieszka.rest.mapper;

import agnieszka.rest.dto.ProductDto;
import agnieszka.rest.entity.Product;
import agnieszka.rest.exception.ProductDoesNotExistException;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static final String MESSAGE = "Product does not exist!";

    public Product toProductEntity(ProductDto productDto) {
        if (productDto == null) {
            throw new ProductDoesNotExistException(MESSAGE);
        }
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .amount(productDto.getAmount())
                .build();
    }

    public ProductDto toProductDto(Product product) {
        if (product == null) {
            throw new ProductDoesNotExistException(MESSAGE);
        }
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .amount(product.getAmount())
                .build();
    }
}
