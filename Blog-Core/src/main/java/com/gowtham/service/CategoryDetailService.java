package com.gowtham.service;

import com.gowtham.dao.CategoryDetailDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.CategoryDetail;
import com.gowtham.vaidator.CategoryDetailValidator;

public class CategoryDetailService {
	final CategoryDetailValidator categoryDetailValidator = new CategoryDetailValidator();
	final CategoryDetailDAO categoryDetailDAO = new CategoryDetailDAO();
	
	public int save(CategoryDetail categoryDetail) throws ServiceException {
		try {
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
	
}
