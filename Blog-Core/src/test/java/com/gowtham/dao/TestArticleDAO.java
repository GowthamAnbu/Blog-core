package com.gowtham.dao;

public class TestArticleDAO {
public static void main(String[] args) {
	final ArticleDAO articleDAO = new ArticleDAO();
	System.out.println(articleDAO.isPresent("Gowtham", "book"));
}
}
