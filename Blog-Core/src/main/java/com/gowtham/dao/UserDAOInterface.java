package com.gowtham.dao;

import java.util.List;

import com.gowtham.model.User;

public interface UserDAOInterface {

	int save(User user);

	int update(User user);

	int delete(Integer id);

	List<User> findAll();

	User findOne(Integer id);

	Boolean isValidUserName(String name);

	Boolean isValidPassword(String name, String password);

	Integer getUserId(String userName);

	String getUserName(Integer userId);

	User getUser(String userName);

	Integer getRole(Integer id);

	List<User> forAdmin();

	void change(Integer id, Integer roleId);

	String getEmailId(Integer userId);

}