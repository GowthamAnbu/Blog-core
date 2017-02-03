package com.gowtham.dao;

public class TestCategoryDAO {

	public static void main(String[] args) {
		CategoryDAO categoryDAO = new CategoryDAO();
		System.out.println(categoryDAO.isPresent(3, "Java"));
	}

}
