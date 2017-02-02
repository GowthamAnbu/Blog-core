package com.gowtham.model;

import lombok.Data;

@Data
public class RatingDetail {
	private Integer id;
	private Article article;
	private User user;
	private Integer rating;
}
