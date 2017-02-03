package com.gowtham.dao;

import com.gowtham.model.User;

public class TestUserDAO {

	public static void main(String[] args) {
		
		User user = new User();
//		user.setId(1);
//		user.setPassword("87654321");
		user.setUserName("Gow");
		UserDAO userDAO = new UserDAO();
		if (userDAO.getUserId(user.getUserName())==null){
			System.out.println("null");
		}
	}

}
