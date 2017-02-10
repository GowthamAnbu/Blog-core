package com.gowtham.dao;

import java.util.List;
import java.util.ListIterator;

public class TestCategoryDAO {

	public static void main(String[] args) {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<String> list=categoryDAO.getCategory(12);
		ListIterator<String> itr=list.listIterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}

}
