package com.gowtham.service;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.Category;
import com.gowtham.model.User;

public class TestCategoryService {
	
public static void main(String[] args) throws ServiceException{
	User user=new User();
	user.setUserName("Gowtham");
	Category category = new Category();
	category.setName("Html");
	category.setUser(user);
	CategoryServiceInterface categoryService = new CategoryService();
	categoryService.addCategory(category);
}

}
