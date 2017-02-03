package com.gowtham.service;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.Article;
import com.gowtham.model.User;

public class TestArticleService {
public static void main(String[] args)throws ServiceException {
	User user = new User();
	user.setUserName("rickson");
	user.setPassword("thisis");
	Article article = new Article();
	article.setName("this");
	article.setContent("that is");
	article.setUser(user);
	ArticleService articleService = new ArticleService();
	System.out.println(articleService.viewAllArticle(user));
	System.out.println(articleService.updateArticle(article));
	System.out.println(articleService.deleteArticle(article));
	System.out.println(article.getUser().getUserName());
}
}
