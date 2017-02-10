package com.gowtham.service;

import com.gowtham.dao.ArticleDAO;
import com.gowtham.dao.CategoryDAO;
import com.gowtham.dao.CategoryDetailDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.CategoryDetail;
import com.gowtham.validator.CategoryDetailValidator;

public class CategoryDetailService {
	final CategoryDetailValidator categoryDetailValidator = new CategoryDetailValidator();
	final CategoryDetailDAO categoryDetailDAO = new CategoryDetailDAO();
	final ArticleDAO articleDAO = new ArticleDAO();
	final CategoryDAO categoryDAO = new CategoryDAO();
	
	public int save(CategoryDetail categoryDetail) throws ServiceException {
		try {
			ArticleDAO articleDAO = new ArticleDAO();
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDetail.getArticle().setId(articleDAO.getArticleId(categoryDetail.getArticle().getName()));
			categoryDetail.getCategory().setId(categoryDAO.getCategoryId(categoryDetail.getCategory().getName()));
			categoryDetailValidator.validateSave(categoryDetail);
			return categoryDetailDAO.save(categoryDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Save",e);
		}
	}
	
	public int update(CategoryDetail categoryDetail) throws ServiceException {
		try {
			categoryDetailValidator.validateUpdate(categoryDetail);
			return categoryDetailDAO.update(categoryDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update",e);
		}
	}
	
	public int delete(CategoryDetail categoryDetail) throws ServiceException {
		try {
			categoryDetailValidator.validateDelete(categoryDetail);
			return categoryDetailDAO.delete(categoryDetail.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete",e);
		}
	}
	
	public void findAll() {
		categoryDetailDAO.findAll();
	}
	
	public int updateCategory(CategoryDetail categoryDetail) throws ServiceException {
		try {
			Integer articleId=articleDAO.getArticleId(categoryDetail.getArticle().getName());
			Integer categoryId=categoryDAO.getCategoryId(categoryDetail.getCategory().getName());
			if(articleId==null){
				throw new ServiceException("invalid article name");
			}
			else if(categoryId==null){
				throw new ServiceException("category doesn't exists");
			}
			categoryDetail.getArticle().setId(articleId);
			categoryDetail.getCategory().setId(categoryId);
			categoryDetailValidator.validateUpdateCategory(categoryDetail);
			return categoryDetailDAO.updateCategory(categoryDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update",e);
		}
	}
	
}
