package com.gowtham.model;

import lombok.Data;

@Data
public class CategoryDetail {
	private Integer id;
	private Article article;
	private Category category;
}
