package com.gowtham.service;

import java.util.List;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.User;

public interface UserServiceInterface {

	int register(User user) throws ServiceException;

	int update(User user) throws ServiceException;

	int delete(User user) throws ServiceException;

	List<User> findAll();

	int login(User user) throws ServiceException;

	Boolean isValidUser(String userName, String password);

	User getUser(String userName);

	Integer getRole(Integer id);

	List<User> forAdmin();

	void change(Integer id, Integer roleId);

	String getEmailId(Integer userId);

}