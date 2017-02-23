package com.gowtham.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gowtham.config.AppConfig;
import com.gowtham.exception.ServiceException;

public class TestCategoryService {
	
public static void main(String[] args) throws ServiceException{
	/*User user=new User();
	user.setUserName("Gowtham");
	Category category = new Category();
	category.setName("Html");
	category.setUser(user);
	CategoryServiceInterface categoryService = new CategoryService();
	categoryService.addCategory(category);*/
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	CategoryServiceInterface categoryService = ctx.getBean(CategoryServiceInterface.class);
	System.out.println(categoryService.findAll());
	ctx.close();
}

}
