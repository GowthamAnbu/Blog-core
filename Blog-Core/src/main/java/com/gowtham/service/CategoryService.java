package com.gowtham.service;

import java.util.List;

import com.gowtham.dao.CategoryDAO;
import com.gowtham.dao.UserDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.Category;
import com.gowtham.validator.CategoryValidator;

public class CategoryService {
	final CategoryValidator categoryValidator = new CategoryValidator();
	final CategoryDAO categoryDAO = new CategoryDAO();
	final UserDAO userDAO = new UserDAO();
	
	public int save(Category category) throws ServiceException {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			category.getUser().setId(userDAO.getUserId(category.getUser().getUserName()));
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

	public int addCategory(Category category) throws ServiceException {
			try {
				category.getUser().setId(userDAO.getUserId(category.getUser().getUserName()));
				categoryValidator.validateSave(category);
				if(categoryDAO.isPresent(category.getUser().getId(), category.getName())){
					throw new ServiceException("category already exists");
				}
				return categoryDAO.save(category);
			} catch (ValidationException e) {
				throw new ServiceException("unable to save",e);
			}
		}
	
	public List<Category> getCategory(Integer id){
		return categoryDAO.listByUserId(id);
	}
	
}
