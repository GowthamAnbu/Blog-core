package com.gowtham.dao;

import java.util.List;

public interface DAO<T> {
	
	int save(T t);
	int update(T t);
	int delete(Integer id);
	List<T> findAll();
	T findOne(Integer id);
	
}
