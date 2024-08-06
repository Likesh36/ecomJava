package com.training.ecommerce.iservices;

import java.util.List;

import com.training.ecommerce.dto.ProductRequest;
import com.training.ecommerce.entities.Product;

public interface IProductService {
	ProductRequest createProduct(ProductRequest productDto);

	ProductRequest getProductById(Long product_id);

	List<ProductRequest> getAllProduct();

	ProductRequest updateProduct(Long product_id,ProductRequest updatedProduct);

	void deleteProduct(Long product_id);

}
