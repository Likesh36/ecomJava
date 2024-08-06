package com.training.ecommerce.iservices;

import java.util.List;

import com.training.ecommerce.entities.Category;

public interface ICategoryService {

	public List getall();

	public Category addbyid(long id);

	public Category getbyid(long id);

	public String deletebyid(long id);
	
	public String CreateNewcategory(Category c);
	
	public Category UpdateCategory(Category c);

}
