package com.gowtham.service;

import com.gowtham.dao.CategoryDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.Category;
import com.gowtham.vaidator.CategoryValidator;

public class CategoryService {
	final CategoryValidator categoryValidator = new CategoryValidator();
	final CategoryDAO categoryDAO = new CategoryDAO();
	
	public int save(Category category) throws ServiceException {
		try {
			categoryValidator.validateSave(category);
			return categoryDAO.save(category);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Save",e);
		}
	}
	
	public int update(Category category) throws ServiceException {
		try {
			categoryValidator.validateUpdate(category);
			return categoryDAO.update(category);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update",e);
		}
	}
	
	public int delete(Category category) throws ServiceException {
		try {
			categoryValidator.validateDelete(category);
			return categoryDAO.delete(category.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete",e);
		}
	}
	
	public void findAll() {
		categoryDAO.findAll();
	}
	
}
