package com.gowtham.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Article {

	private Integer id;
	private User user;
	private String name;
	private String content;
	private LocalDateTime publishedDate;
	private LocalDateTime modifiedDate;
	
}
