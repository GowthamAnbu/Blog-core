package com.gowtham.dao;

import java.util.List;

import com.gowtham.model.CommentDetail;

public interface CommentDetailDAOInterface {

	int save(CommentDetail commentDetail);

	int update(CommentDetail commentDetail);

	int delete(Integer id);

	List<CommentDetail> findAll();

	CommentDetail findOne(Integer id);

	List<CommentDetail> getComments(Integer articleId);

}