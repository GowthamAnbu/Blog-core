package com.gowtham.service;

import java.util.List;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.Article;
import com.gowtham.model.Category;

public interface CategoryServiceInterface {

	int save(Category category) throws ServiceException;

	int update(Category category) throws ServiceException;

	int delete(Category category) throws ServiceException;

	void findAll();

	int addCategory(Category category) throws ServiceException;

	List<Category> getCategory(Integer id);

	List<Article> listByCategory(String categoryName);

}