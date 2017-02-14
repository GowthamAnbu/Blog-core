package com.gowtham.service;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.User;

public class TestUserService {
public static void main(String[] args) throws ServiceException{
	User user = new User();
	user.setName("Cole");
	user.setUserName("baliton");
	user.setPassword("word");
	user.setEmailId("abc@gmail.com");
	user.setPhoneNumber("123456789");
	
	final UserService userService = new UserService();
//	System.out.println(userService.register(user));
//	System.out.println(userService.login(user));
	System.out.println(userService.getUser("Gowtham"));
}
}
