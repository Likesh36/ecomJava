package com.training.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import com.training.ecommerce.advice.ResourceNotFoundException;
import com.training.ecommerce.dto.ProductRequest;
import com.training.ecommerce.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.training.ecommerce.entities.Product;
import com.training.ecommerce.iservices.IProductService;
import com.training.ecommerce.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {


	private ProductRepository productRepository;

	@Override
	public ProductRequest createProduct(ProductRequest productDto) {

		Product product = ProductMapper.mapToProduct(productDto);
		Product savedProduct = productRepository.save(product);

		return ProductMapper.mapToProductDto(savedProduct);
	}

	@Override
	public ProductRequest getProductById(Long product_id) {
		Product product = productRepository.findById(product_id)
				.orElseThrow(()->
						new ResourceNotFoundException("Product is not exists with the given id: "+ product_id));
		return ProductMapper.mapToProductDto(product);
	}

	@Override
	public List<ProductRequest> getAllProduct() {
		List<Product> products  =  productRepository.findAll();
		return products.stream().map((product)-> ProductMapper.mapToProductDto(product))
				.collect(Collectors.toList());
	}

	@Override
	public ProductRequest updateProduct(Long product_id, ProductRequest updatedProduct) {
		Product product  = productRepository.findById(product_id).orElseThrow(
				()->new ResourceNotFoundException("Product does not exist with the give id:" + product_id)
		);

		product.setProductId(updatedProduct.getProductId());
		product.setDescription(updatedProduct.getDescription());
		product.setName(updatedProduct.getName());
		product.setCategoryId(updatedProduct.getCategoryId());
		product.setPrice(updatedProduct.getPrice());
		product.setImageUrl(updatedProduct.getImageUrl());
		product.setStock(updatedProduct.getStock());
		product.setStatus(updatedProduct.getStatus());
		product.setCreated_by(updatedProduct.getCreated_by());
		product.setUpdated_by(updatedProduct.getUpdated_by());

		Product updatedProductObj =  productRepository.save(product);
		return ProductMapper.mapToProductDto(updatedProductObj);
	}

	@Override
	public void deleteProduct(Long product_id) {
		Product product  = productRepository.findById(product_id).orElseThrow(
				()->new ResourceNotFoundException("Product does not exist with the give id:" + product_id)
		);
		productRepository.deleteById(product_id);
	}



}
