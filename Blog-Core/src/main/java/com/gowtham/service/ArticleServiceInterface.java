package com.gowtham.service;

import java.util.List;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.CategoryDetail;
import com.gowtham.model.User;

public interface ArticleServiceInterface {

	String post(Article article, Category category, CategoryDetail categoryDetail) throws ServiceException;

	int save(Article article) throws ServiceException;

	int update(Article article) throws ServiceException;

	void delete(Integer id);

	List<Article> findAll();

	List<Article> viewAllArticle(User user) throws ServiceException;

	List<Article> viewAllArticle() throws ServiceException;

	int updateArticle(Article article) throws ServiceException;

	int deleteArticle(Article article) throws ServiceException;

	int viewArticle(CategoryDetail categoryDetail) throws ServiceException;

	int publishSave(Article article) throws ServiceException;

	Integer getUserId(Integer articleId);

}