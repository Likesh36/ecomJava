package com.training.ecommerce.controllers;

import java.util.List;

import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.ecommerce.entities.Category;
import com.training.ecommerce.repository.CategoryRepository;
import com.training.ecommerce.services.CategoryService;
import com.training.ecommerce.services.ProductService;

		
	@RestController
	@RequestMapping("/api/Products/Category")
	public class CategoryController {

		@Autowired
		public CategoryService categoryservice;
		
		
		@GetMapping("/getall")
		public List getall()
		{
			return categoryservice.getall();
		}
	   
		@DeleteMapping("/deletebyid/{id}")
		public String deleteall(@PathVariable ("id") long id)
		{
			 categoryservice.deletebyid(id);
			 return "deleted";
		}
		@PostMapping("/addcategory")
		public ResponseEntity<?> Addcategory(@RequestBody Category c)
		{
			try {
				return new ResponseEntity<>(categoryservice.CreateNewcategory(c),HttpStatus.OK);
				
			}catch(Exception e)
			{
				return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}

		@PutMapping("/updatecategory/{id}")
		public ResponseEntity<?> updatecategory(@RequestBody Category c, @PathVariable long id)
		{
			try {
				return new ResponseEntity<>(categoryservice.UpdateCategory(c),HttpStatus.OK);
				
			}catch(Exception e)
			{
				return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		

}
