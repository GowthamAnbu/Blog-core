package com.gowtham.model;

import lombok.Data;

@Data
public class CommentDetail {
	private Integer id;
	private Article article;
	private User user;
	private String comment;
}
