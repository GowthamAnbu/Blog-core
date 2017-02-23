package com.gowtham.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gowtham.config.AppConfig;
import com.gowtham.exception.ServiceException;
import com.gowtham.model.User;

public class TestUserService {
public static void main(String[] args) throws ServiceException{
	User user = new User();
	user.setName("abc");
	user.setUserName("abc");
	user.setPassword("abc");
	user.setEmailId("ticketmanagement.tms@gmail.com");
	user.setPhoneNumber("123456789");
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	UserServiceInterface userService=ctx.getBean(UserServiceInterface.class);
//	System.out.println(userService.register(user));
//	System.out.println(userService.login(user));
//	System.out.println(userService.getUser("Gowtham"));
//	System.out.println(userService.findAll());
	userService.register(user);
	ctx.close();
}
}
