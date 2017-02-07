package com.gowtham.service;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.CategoryDetail;

public class TestCategoryDetailService {
	
public static void main(String[] args) throws ServiceException{
	CategoryDetailService categoryDetailService = new CategoryDetailService();
	CategoryDetail categoryDetail = new CategoryDetail();
	Category category = new Category();
	category.setName("c");
	Article article = new Article();
	article.setName("MyFirst");
	categoryDetail.setCategory(category);
	categoryDetail.setArticle(article);
	categoryDetailService.updateCategory(categoryDetail);
}

}