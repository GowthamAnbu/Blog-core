package com.gowtham.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gowtham.config.AppConfig;
import com.gowtham.dao.CommentDetailDAOInterface;

public class TestCommentDetail {
	
public static void main(String[] args) {
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	CommentDetailDAOInterface commentDetailDAO=ctx.getBean(CommentDetailDAOInterface.class);
	System.out.println(commentDetailDAO.findAll());
	ctx.close();
}

}
