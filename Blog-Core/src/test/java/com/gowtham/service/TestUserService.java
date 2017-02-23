package com.gowtham.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gowtham.config.AppConfig;
import com.gowtham.exception.ServiceException;

public class TestUserService {
public static void main(String[] args) throws ServiceException{
	/*User user = new User();
	user.setName("Cole");
	user.setUserName("baliton");
	user.setPassword("word");
	user.setEmailId("abc@gmail.com");
	user.setPhoneNumber("123456789");*/
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	UserServiceInterface userService=ctx.getBean(UserServiceInterface.class);
//	System.out.println(userService.register(user));
//	System.out.println(userService.login(user));
//	System.out.println(userService.getUser("Gowtham"));
	System.out.println(userService.findAll());
	ctx.close();
}
}
