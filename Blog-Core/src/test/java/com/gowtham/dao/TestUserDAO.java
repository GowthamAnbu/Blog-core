package com.gowtham.dao;

import com.gowtham.model.User;

public class TestUserDAO {

	public static void main(String[] args) {
		
		User user = new User();/*
		user.setId(1);
		user.setPassword("87654321");*/
		user.setUserName("Gowtham");
		UserDAO userDAO = new UserDAO();
	/*	UserDAO userDAO = new UserDAO();
		if (userDAO.getUserId(user.getUserName())==null){
			System.out.println("null");
		}
		else
			System.out.println(userDAO.getUserId(user.getUserName()));
	}*/
		System.out.println(userDAO.forAdmin());
}
}