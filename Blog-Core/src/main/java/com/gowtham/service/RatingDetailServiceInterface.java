package com.gowtham.service;

import java.util.List;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.RatingDetail;

public interface RatingDetailServiceInterface {

	int save(RatingDetail ratingDetail) throws ServiceException;

	int update(RatingDetail ratingDetail) throws ServiceException;

	int delete(RatingDetail ratingDetail) throws ServiceException;

	List<RatingDetail> findAll();

}