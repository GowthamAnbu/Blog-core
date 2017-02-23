package com.gowtham.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gowtham.dao.ArticleDAOInterface;
import com.gowtham.dao.CategoryDAOInterface;
import com.gowtham.dao.CategoryDetailDAOInterface;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.CategoryDetail;
import com.gowtham.validator.CategoryDetailValidator;

@Service
public class CategoryDetailService implements CategoryDetailServiceInterface {
	CategoryDetailValidator categoryDetailValidator = new CategoryDetailValidator();
	@Autowired
	CategoryDetailDAOInterface categoryDetailDAO;
	@Autowired
	ArticleDAOInterface articleDAO;
	@Autowired
	CategoryDAOInterface categoryDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryDetailServiceInterface#save(com.gowtham.model
	 * .CategoryDetail)
	 */
	@Override
	public int save(CategoryDetail categoryDetail) throws ServiceException {
		try {
			categoryDetail.getArticle().setId(articleDAO.getArticleId(categoryDetail.getArticle().getName()));
			categoryDetail.getCategory().setId(categoryDAO.getCategoryId(categoryDetail.getCategory().getName()));
			categoryDetailValidator.validateSave(categoryDetail);
			return categoryDetailDAO.save(categoryDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Save", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryDetailServiceInterface#update(com.gowtham.
	 * model.CategoryDetail)
	 */
	@Override
	public int update(CategoryDetail categoryDetail) throws ServiceException {
		try {
			categoryDetailValidator.validateUpdate(categoryDetail);
			return categoryDetailDAO.update(categoryDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryDetailServiceInterface#delete(com.gowtham.
	 * model.CategoryDetail)
	 */
	@Override
	public int delete(CategoryDetail categoryDetail) throws ServiceException {
		try {
			categoryDetailValidator.validateDelete(categoryDetail);
			return categoryDetailDAO.delete(categoryDetail.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.CategoryDetailServiceInterface#findAll()
	 */
	@Override
	public void findAll() {
		categoryDetailDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryDetailServiceInterface#updateCategory(com.
	 * gowtham.model.CategoryDetail)
	 */
	@Override
	public int updateCategory(CategoryDetail categoryDetail) throws ServiceException {
		try {
			Integer articleId = articleDAO.getArticleId(categoryDetail.getArticle().getName());
			Integer categoryId = categoryDAO.getCategoryId(categoryDetail.getCategory().getName());
			if (articleId == null) {
				throw new ServiceException("invalid article name");
			} else if (categoryId == null) {
				throw new ServiceException("category doesn't exists");
			}
			categoryDetail.getArticle().setId(articleId);
			categoryDetail.getCategory().setId(categoryId);
			categoryDetailValidator.validateUpdateCategory(categoryDetail);
			return categoryDetailDAO.updateCategory(categoryDetail);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

}
