package com.gowtham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gowtham.dao.RatingDetailDAOInterface;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.RatingDetail;
import com.gowtham.validator.RatingDetailValidator;
@Service
public class RatingDetailService implements RatingDetailServiceInterface {
	RatingDetailValidator ratingDetailValidator = new RatingDetailValidator();
	@Autowired
	RatingDetailDAOInterface ratingDetailDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.RatingDetailServiceInterface#save(com.gowtham.model.
	 * RatingDetail)
	 */
	@Override
	public int save(RatingDetail ratingDetail) throws ServiceException {
		try {
			ratingDetailValidator.validateSave(ratingDetail);
			return ratingDetailDAO.save(ratingDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Save", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.RatingDetailServiceInterface#update(com.gowtham.model
	 * .RatingDetail)
	 */
	@Override
	public int update(RatingDetail ratingDetail) throws ServiceException {
		try {
			ratingDetailValidator.validateUpdate(ratingDetail);
			return ratingDetailDAO.update(ratingDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.RatingDetailServiceInterface#delete(com.gowtham.model
	 * .RatingDetail)
	 */
	@Override
	public int delete(RatingDetail ratingDetail) throws ServiceException {
		try {
			ratingDetailValidator.validateDelete(ratingDetail);
			return ratingDetailDAO.delete(ratingDetail.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.RatingDetailServiceInterface#findAll()
	 */
	@Override
	public List<RatingDetail> findAll() {
		return ratingDetailDAO.findAll();
	}

}
