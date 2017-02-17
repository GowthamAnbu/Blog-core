package com.gowtham.dao;

import java.util.List;

import com.gowtham.model.Article;
import com.gowtham.model.Category;

public interface CategoryDAOInterface {

	int save(Category category);

	int update(Category category);

	int delete(Integer id);

	List<Category> findAll();

	Category findOne(Integer id);

	Integer getCategoryId(String categoryName);

	boolean isPresent(Integer userId, String categoryName);

	List<String> getCategory(Integer id);

	List<Category> listByUserId(int userId);

	List<Article> listByCategory(String categoryName);

}