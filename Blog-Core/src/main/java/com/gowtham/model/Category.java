package com.gowtham.model;

import lombok.Data;

@Data
public class Category {
	private Integer id;
	private String name;
	private User user;
}
