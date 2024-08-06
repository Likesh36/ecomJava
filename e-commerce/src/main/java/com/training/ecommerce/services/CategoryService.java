package com.training.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ecommerce.entities.Category;
import com.training.ecommerce.iservices.ICategoryService;
import com.training.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	public CategoryRepository categoryrepo;

	public CategoryService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List getall() {
		// TODO Auto-generated method stub
		return categoryrepo.findAll();
	}

	@Override
	public Category getbyid(long id) {
		// TODO Auto-generated method stub
		return categoryrepo.getById(id);
	}

	@Override
	public Category addbyid(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletebyid(long id) {
		// TODO Auto-generated method stub
		categoryrepo.deleteById(id);
		return "deleted";
	}

	@Override
	public String CreateNewcategory(Category c) {
		// TODO Auto-generated method stub
		this.categoryrepo.save(c);
		return "added";
	}
//	
//	@Override
//	public String UpdateCategory(Category c,long id) {
//Optional<Category> category=categoryrepo.findById(id);
//	this.categoryrepo.save(category);
//	return "updated";
//	}
    @Override
	public Category UpdateCategory(Category updatedCategory) {
    	
    	Optional<Category> catogoryobj = categoryrepo.findById(updatedCategory.getId());
    	if(catogoryobj.isPresent())
    	{
    		Category cateobj= catogoryobj.get();
    		cateobj.setId(updatedCategory.getId());
    		cateobj.setName(updatedCategory.getName());
    		cateobj.setDescription(updatedCategory.getDescription());
    		Category cateobj1=categoryrepo.save(cateobj);
    		return cateobj1;
    	}
    	else
    	{
    		Category categorys=categoryrepo.save(updatedCategory);
    		return categorys;
    	}
    		
//        Optional<Category> categoryOptional = categoryrepo.findById(id);
//        if (categoryOptional.isPresent()) {
//            Category existingCategory = categoryOptional.get();
//            existingCategory.setName(updatedCategory.getName());
//            existingCategory.setDescription(updatedCategory.getDescription());
//            categoryrepo.save(existingCategory);
//            return "updated";
//        } else {
//            throw new RuntimeException("Category not found with id: " + id);
//        }
//    }
    }
	

}
