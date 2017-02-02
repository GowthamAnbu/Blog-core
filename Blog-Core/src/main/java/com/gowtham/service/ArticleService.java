package com.gowtham.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gowtham.dao.ArticleDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.CategoryDetail;
import com.gowtham.vaidator.ArticleValidator;

public class ArticleService {
	final ArticleValidator articleValidator = new ArticleValidator();
	final ArticleDAO articleDAO = new ArticleDAO();
	final CategoryService categoryService = new CategoryService();
	final CategoryDetailService categoryDetailService = new CategoryDetailService();
	
	@Transactional
	public String post(Article article,Category category,CategoryDetail categoryDetail) throws ServiceException {
		try {
			save(article);
			categoryService.save(category);
			categoryDetailService.save(categoryDetail);
		} catch (ServiceException e) {
			throw new ServiceException("Unable to Post",e);
		}
		return "POSTED SUCCESSFULLY";
	}

	public int save(Article article) throws ServiceException {
		try {
			articleValidator.validateSave(article);
			return articleDAO.save(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to save",e);
		}
	}

	public int update(Article article) throws ServiceException {
		try {
			articleValidator.validateUpdate(article);
			return articleDAO.update(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update",e);
		}
	}

	public int delete(Article article) throws ServiceException {
		try {
			articleValidator.validateDelete(article);
			return articleDAO.delete(article.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to delete",e);
		}
	}

	public List<Article> findAll() {
		return articleDAO.findAll();
	}
		
}
