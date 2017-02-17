package com.gowtham.dao;

import java.util.List;

import com.gowtham.model.RatingDetail;

public interface RatingDetailDAOInterface {

	int save(RatingDetail ratingDetail);

	int update(RatingDetail ratingDetail);

	int delete(Integer id);

	List<RatingDetail> findAll();

	RatingDetail findOne(Integer id);

}