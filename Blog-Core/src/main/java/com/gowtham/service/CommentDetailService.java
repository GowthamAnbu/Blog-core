package com.gowtham.service;

import java.util.List;

import com.gowtham.dao.CommentDetailDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.CommentDetail;
import com.gowtham.validator.CommentDetailValidator;

public class CommentDetailService {
	final CommentDetailValidator commentDetailValidator = new CommentDetailValidator();
	final CommentDetailDAO commentDetailDAO = new CommentDetailDAO();
	
	public int save(CommentDetail commentDetail) throws ServiceException {
		try {
			commentDetailValidator.validateSave(commentDetail);
			return commentDetailDAO.save(commentDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Save",e);
		}
	}
	
	public int update(CommentDetail commentDetail) throws ServiceException {
		try {
			commentDetailValidator.validateUpdate(commentDetail);
			return commentDetailDAO.update(commentDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update",e);
		}
	}
	
	public int delete(CommentDetail commentDetail) throws ServiceException {
		try {
			commentDetailValidator.validateDelete(commentDetail);
			return commentDetailDAO.delete(commentDetail.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete",e);
		}
	}
	
	public void findAll() {
		commentDetailDAO.findAll();
	}
	
	public List<CommentDetail> getComments(Integer articleId){
		CommentDetailDAO commentDetailDAO= new CommentDetailDAO();
		return commentDetailDAO.getComments(articleId);
	}
	
}
