package com.gowtham.service;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.CategoryDetail;

public interface CategoryDetailServiceInterface {

	int save(CategoryDetail categoryDetail) throws ServiceException;

	int update(CategoryDetail categoryDetail) throws ServiceException;

	int delete(CategoryDetail categoryDetail) throws ServiceException;

	void findAll();

	int updateCategory(CategoryDetail categoryDetail) throws ServiceException;

}