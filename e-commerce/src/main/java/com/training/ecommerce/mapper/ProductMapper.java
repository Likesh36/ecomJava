package com.training.ecommerce.mapper;
import com.training.ecommerce.dto.ProductRequest;
import com.training.ecommerce.entities.Product;

public class ProductMapper {
    public static ProductRequest mapToProductDto(Product product){
        return new ProductRequest(
                product.getProductId(),
                product.getDescription(),
                product.getName(),
                product.getCategoryId(),
                product.getPrice(),
                product.getImageUrl(),
                product.getStock(),
                product.getStatus(),
                product.getCreated_by(),
                product.getUpdated_by()
        );
    }

    public static Product mapToProduct(ProductRequest productDto){
        return new Product(
                productDto.getProductId(),
                productDto.getDescription(),
                productDto.getName(),
                productDto.getCategoryId(),
                productDto.getPrice(),
                productDto.getImageUrl(),
                productDto.getStock(),
                productDto.getStatus(),
                productDto.getCreated_by(),
                productDto.getUpdated_by()
        );
    }
}
