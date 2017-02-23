package com.gowtham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gowtham.dao.UserDAOInterface;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.User;
import com.gowtham.validator.UserValidator;

@Service
public class UserService implements UserServiceInterface {
	private UserValidator userValidator = new UserValidator();
	@Autowired
	private UserDAOInterface userDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.UserServiceInterface#register(com.gowtham.model.User)
	 */
	@Override
	public int register(User user) throws ServiceException {
		try {
			userValidator.validateSave(user);
			if (!userDAO.isValidUserName(user.getUserName())) {
				throw new ServiceException("user name already exists");
			}
			return userDAO.save(user);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Register", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.UserServiceInterface#update(com.gowtham.model.User)
	 */
	@Override
	public int update(User user) throws ServiceException {
		try {
			userValidator.validateUpdate(user);
			if (userDAO.isValidUserName(user.getUserName())) {
				throw new ServiceException("user name doesn't exists");
			}
			return userDAO.update(user);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.UserServiceInterface#delete(com.gowtham.model.User)
	 */
	@Override
	public int delete(User user) throws ServiceException {
		try {
			userValidator.validateDelete(user);
			if (userDAO.isValidUserName(user.getUserName())) {
				throw new ServiceException("user name doesn't exists");
			}
			return userDAO.delete(user.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.UserServiceInterface#findAll()
	 */
	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.UserServiceInterface#login(com.gowtham.model.User)
	 */
	@Override
	public int login(User user) throws ServiceException {
		try {
			userValidator.validateUpdate(user);
			if (userDAO.isValidUserName(user.getUserName())) {
				throw new ServiceException("Invalid User Name");
			} else if (!userDAO.isValidPassword(user.getUserName(), user.getPassword())) {
				throw new ServiceException("Invalid Password");
			}
			return 1;
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Login", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.UserServiceInterface#isValidUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean isValidUser(String userName, String password) {
		if (userDAO.isValidPassword(userName, password)) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.UserServiceInterface#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String userName) {
		return userDAO.getUser(userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.UserServiceInterface#getRole(java.lang.Integer)
	 */
	@Override
	public Integer getRole(Integer id) {
		return userDAO.getRole(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.UserServiceInterface#forAdmin()
	 */
	@Override
	public List<User> forAdmin() {
		return userDAO.forAdmin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.UserServiceInterface#change(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public void change(Integer id, Integer roleId) {
		userDAO.change(id, roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.UserServiceInterface#getEmailId(java.lang.Integer)
	 */
	@Override
	public String getEmailId(Integer userId) {
		return userDAO.getEmailId(userId);
	}
}
