package com.gowtham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gowtham.dao.CommentDetailDAOInterface;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.CommentDetail;
import com.gowtham.validator.CommentDetailValidator;

@Service
public class CommentDetailService implements CommentDetailServiceInterface {
	CommentDetailValidator commentDetailValidator = new CommentDetailValidator();
	@Autowired
	CommentDetailDAOInterface commentDetailDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CommentDetailServiceInterface#save(com.gowtham.model.
	 * CommentDetail)
	 */
	@Override
	public int save(CommentDetail commentDetail) throws ServiceException {
		try {
			commentDetailValidator.validateSave(commentDetail);
			return commentDetailDAO.save(commentDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Save", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CommentDetailServiceInterface#update(com.gowtham.
	 * model.CommentDetail)
	 */
	@Override
	public int update(CommentDetail commentDetail) throws ServiceException {
		try {
			commentDetailValidator.validateUpdate(commentDetail);
			return commentDetailDAO.update(commentDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CommentDetailServiceInterface#delete(com.gowtham.
	 * model.CommentDetail)
	 */
	@Override
	public int delete(CommentDetail commentDetail) throws ServiceException {
		try {
			commentDetailValidator.validateDelete(commentDetail);
			return commentDetailDAO.delete(commentDetail.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.CommentDetailServiceInterface#findAll()
	 */
	@Override
	public void findAll() {
		commentDetailDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CommentDetailServiceInterface#getComments(java.lang.
	 * Integer)
	 */
	@Override
	public List<CommentDetail> getComments(Integer articleId) {
		return commentDetailDAO.getComments(articleId);
	}

}
