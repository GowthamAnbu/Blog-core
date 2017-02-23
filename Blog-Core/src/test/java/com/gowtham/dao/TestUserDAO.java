package com.gowtham.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gowtham.config.AppConfig;

public class TestUserDAO {

	public static void main(String[] args) {
		/*
		User user = new User();
		user.setId(1);
		user.setPassword("87654321");
		user.setUserName("Gowtham");*/
		 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		 UserDAOInterface userDAO = ctx.getBean(UserDAOInterface.class);
		/*if (userDAO.getUserId(user.getUserName())==null){
			System.out.println("null");
		}
		else
			System.out.println(userDAO.getUserId(user.getUserName()));
	}*/
//		UserDAO userDAO=new UserDAO();
		System.out.println(userDAO.findAll());
		
		ctx.close();
}
}