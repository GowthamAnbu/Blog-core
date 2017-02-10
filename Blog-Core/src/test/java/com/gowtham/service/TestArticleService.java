package com.gowtham.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ListIterator;

import com.gowtham.dao.ArticleDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.model.Article;
import com.gowtham.model.User;
public class TestArticleService {
public static void main(String[] args)throws ServiceException {
	User user = new User();
	user.setUserName("Gowtham");
	user.setPassword("thisis");
	Article article = new Article();
	article.setName("newtest");
	article.setContent("content");
	article.setUser(user);
	ArticleService articleService = new ArticleService();
	ArticleDAO articleDAO = new ArticleDAO();
	List<Article> articleList=articleService.viewAllArticle(user);
	ListIterator<Article> articleIterator=articleList.listIterator();
	while(articleIterator.hasNext())
	{
		System.out.println(articleIterator.next());
	}
	System.out.println(articleService.updateArticle(article));
	System.out.println(articleService.deleteArticle(article));
	System.out.println(article.getUser().getUserName());
	System.out.println(articleService.publishSave(article));
	Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
	System.out.println(timestamp);
	System.out.println(articleDAO.isPresent(article.getUser().getUserName(), article.getName()));
	System.out.println(articleService.publishSave(article));
}
}
