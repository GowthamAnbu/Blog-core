package com.gowtham.service;

import java.util.List;

import com.gowtham.dao.UserDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.User;
import com.gowtham.vaidator.UserValidator;

public class UserService {
	private UserValidator userValidator = new UserValidator();
	private UserDAO userDAO = new UserDAO();

	public int register(User user) throws ServiceException {
		try {
			userValidator.validateSave(user);
			if (!userDAO.isValid(user.getUserName())) {
				throw new ServiceException("user name already exists");
			}
			return userDAO.save(user);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Register",e);
		}
	}

	public int update(User user) throws ServiceException {
		try {
			userValidator.validateUpdate(user);
			if (userDAO.isValid(user.getUserName())) {
				throw new ServiceException("user name doesn't exists");
			}
			return userDAO.update(user);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update",e);
		}
	}

	public int delete(User user) throws ServiceException {
		try {
			userValidator.validateDelete(user);
			if (userDAO.isValid(user.getUserName())) {
				throw new ServiceException("user name doesn't exists");
			}
			return userDAO.delete(user.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete",e);
		}
	}
	
	public List<User> findAll(){
		return userDAO.findAll();
	}
	
}
