package com.gowtham.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gowtham.config.AppConfig;

public class TeastRatingDetailService {
public static void main(String[] args) {
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	RatingDetailService ratingService=ctx.getBean(RatingDetailService.class);
	System.out.println(ratingService.findAll());
	ctx.close();
}
}
