package com.gowtham.service;

import java.util.List;

import com.gowtham.dao.UserDAO;
import com.gowtham.dao.UserDAOInterface;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.User;
import com.gowtham.validator.UserValidator;

public class UserService {
	private UserValidator userValidator = new UserValidator();
	private UserDAOInterface userDAO = new UserDAO();

	public int register(User user) throws ServiceException {
		try {
			userValidator.validateSave(user);
			if (!userDAO.isValidUserName(user.getUserName())) {
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
			if (userDAO.isValidUserName(user.getUserName())) {
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
			if (userDAO.isValidUserName(user.getUserName())) {
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
	
	public int login(User user) throws ServiceException {
		try {
			userValidator.validateUpdate(user);
			if (userDAO.isValidUserName(user.getUserName())) {
				throw new ServiceException("Invalid User Name");
			}
			else if(!userDAO.isValidPassword(user.getUserName(),user.getPassword())){
				throw new ServiceException("Invalid Password");
			}
			return 1;
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Login",e);
		}
	}
	
	public Boolean isValidUser(String userName,String password){
		UserDAOInterface userDAO = new UserDAO();
		if(userDAO.isValidPassword(userName, password)){
			return true;
		}
		return false;
	}
	
	public User getUser(String userName) {
		UserDAOInterface userDAO = new UserDAO();
		return userDAO.getUser(userName);
	}
	
	public Integer getRole(Integer id){
		UserDAOInterface userDAO = new UserDAO();
		return userDAO.getRole(id);
	}
	
	public List<User> forAdmin(){
		UserDAOInterface userDAO = new UserDAO();
		return userDAO.forAdmin();
	}
	
	public void change(Integer id,Integer roleId){
		final UserDAOInterface userDAO = new UserDAO();
		userDAO.change(id, roleId);
	}
	
	public String getEmailId(Integer userId){
		UserDAOInterface userDAO = new UserDAO();
		return userDAO.getEmailId(userId);
	}
}
