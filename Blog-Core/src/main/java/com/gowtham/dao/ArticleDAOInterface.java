package com.gowtham.dao;

import java.util.List;

import com.gowtham.model.Article;
import com.gowtham.model.User;

public interface ArticleDAOInterface {

	int save(Article article);

	int update(Article article);

	int delete(Integer id);

	List<Article> findAll();

	Article findOne(Integer id);

	List<Article> viewAll(User user);

	List<Article> viewAll();

	Integer viewAllCheck(Integer userId);

	Integer getArticleId(String articleName);

	Article viewArticle(Integer id);

	int updateArticle(Article article);

	Boolean isPresent(String userName, String articleName);

	Integer getUserId(Integer articleId);

}