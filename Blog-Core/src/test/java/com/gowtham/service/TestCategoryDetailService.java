package com.gowtham.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gowtham.config.AppConfig;
import com.gowtham.exception.ServiceException;

public class TestCategoryDetailService {
	
public static void main(String[] args) throws ServiceException{
	/*CategoryDetailServiceInterface categoryDetailService = new CategoryDetailService();
	CategoryDetail categoryDetail = new CategoryDetail();
	Category category = new Category();
	category.setName("c");
	Article article = new Article();
	article.setName("MyFirst");
	categoryDetail.setCategory(category);
	categoryDetail.setArticle(article);
	categoryDetailService.updateCategory(categoryDetail);*/
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	CategoryDetailService categoryService=ctx.getBean(CategoryDetailService.class);
	categoryService.findAll();
	ctx.close();
}

}
