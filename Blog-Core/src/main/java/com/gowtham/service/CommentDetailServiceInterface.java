package com.gowtham.service;

import java.util.List;

import com.gowtham.exception.ServiceException;
import com.gowtham.model.CommentDetail;

public interface CommentDetailServiceInterface {

	int save(CommentDetail commentDetail) throws ServiceException;

	int update(CommentDetail commentDetail) throws ServiceException;

	int delete(CommentDetail commentDetail) throws ServiceException;

	void findAll();

	List<CommentDetail> getComments(Integer articleId);

}