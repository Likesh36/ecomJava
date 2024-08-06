package com.training.ecommerce.controllers;

import java.util.List;

import com.training.ecommerce.dto.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.ecommerce.services.ProductService;

	@AllArgsConstructor
	@RestController
	@RequestMapping("/api/Products")
	@CrossOrigin(origins="http://localhost:4200")
	public class ProductController {
		private ProductService productService;

		// add product REST API
		@PostMapping("/create")
		public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productDto){
			ProductRequest savedProduct = productService.createProduct(productDto);
			return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		}


		// get Product REST API
		@GetMapping("{id}")
		public ResponseEntity<ProductRequest> getProductById(@PathVariable("id") long product_id){
			ProductRequest productDto = productService.getProductById(product_id);
			return ResponseEntity.ok(productDto);
		}

		// get All product REST API
		@GetMapping("/getAll")
		public ResponseEntity<List<ProductRequest>> getAllProduct(){
			List<ProductRequest> productList = productService.getAllProduct();
			return ResponseEntity.ok(productList);
		}

		//update product REST API
		@PutMapping("{id}")
		public ResponseEntity<ProductRequest> updateProduct(@PathVariable("id") Long product_id,
														@RequestBody ProductRequest updatedProduct){
			ProductRequest productDto = productService.updateProduct(product_id,updatedProduct);
			return ResponseEntity.ok(productDto);
		}


		//delete product REST API
		@DeleteMapping("{id}")
		public ResponseEntity<String> deleteProduct(@PathVariable("id") Long product_id){
			productService.deleteProduct(product_id);
			return ResponseEntity.ok("Product deleted successfully");
		}

}
