package com.gowtham.dao;

import com.gowtham.model.User;

public class TestUserDAO {

	public static void main(String[] args) {
		
		User user = new User();
		user.setId(1);
		user.setPassword("87654321");
		
		UserDAO userDAO = new UserDAO();
		System.out.println(userDAO.update(user));
	}

}
