package com.gowtham.dao;

import java.util.List;

import com.gowtham.model.CategoryDetail;

public interface CategoryDetailDAOInterface {

	int save(CategoryDetail categoryDetail);

	int update(CategoryDetail categoryDetail);

	int delete(Integer id);

	List<CategoryDetail> findAll();

	CategoryDetail findOne(Integer id);

	int updateCategory(CategoryDetail categoryDetail);

	Integer getArticleId(Integer id);

}