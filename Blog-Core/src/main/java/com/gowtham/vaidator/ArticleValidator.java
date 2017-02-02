package com.gowtham.vaidator;

import java.time.LocalDateTime;

import com.gowtham.exception.ValidationException;
import com.gowtham.model.Article;
import com.gowtham.util.ValidationUtil;

public class ArticleValidator {
	
	private void validateArticle(Article article) throws ValidationException {
		ValidationUtil.isNotValid(article,"Invalid User");
	}
	
	private void validateId(Integer id)throws ValidationException{
		ValidationUtil.isNotValid(id,"Invalid Id");
	}
	
	private void validateUserId(Integer userId)throws ValidationException{
		ValidationUtil.isNotValid(userId,"Invalid UserId");
	}
	
	private void validateName(String name)throws ValidationException{
		ValidationUtil.isNotValid(name,"Invalid Name");
	}
	
	private void validateContent(String content)throws ValidationException{
		ValidationUtil.isNotValid(content,"Invalid Content");
	}
		
	private void validateModifiedDate(LocalDateTime modifiedDate)throws ValidationException{
		ValidationUtil.isNotValid(modifiedDate,"Invalid PublishedDate");
	}

	
	public void validateSave(Article article) throws ValidationException{
		validateArticle(article);
		validateUserId(article.getUser().getId());
		validateName(article.getName());
		validateContent(article.getContent());
	}
	
	public void validateUpdate(Article article) throws ValidationException{
		validateArticle(article);
		validateId(article.getId());
		validateModifiedDate(article.getModifiedDate());
		validateContent(article.getContent());
	}
	
	public void validateDelete(Article article) throws ValidationException{
		validateArticle(article);
		validateId(article.getId());
	}
	
}
