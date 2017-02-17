package com.gowtham.service;

import java.util.List;

import com.gowtham.dao.CategoryDAO;
import com.gowtham.dao.CategoryDAOInterface;
import com.gowtham.dao.UserDAO;
import com.gowtham.dao.UserDAOInterface;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.validator.CategoryValidator;

public class CategoryService implements CategoryServiceInterface {
	final CategoryValidator categoryValidator = new CategoryValidator();
	final CategoryDAOInterface categoryDAO = new CategoryDAO();
	final UserDAOInterface userDAO = new UserDAO();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.CategoryServiceInterface#save(com.gowtham.model.
	 * Category)
	 */
	@Override
	public int save(Category category) throws ServiceException {
		try {
			CategoryDAOInterface categoryDAO = new CategoryDAO();
			category.getUser().setId(userDAO.getUserId(category.getUser().getUserName()));
			categoryValidator.validateSave(category);
			return categoryDAO.save(category);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Save", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryServiceInterface#update(com.gowtham.model.
	 * Category)
	 */
	@Override
	public int update(Category category) throws ServiceException {
		try {
			categoryValidator.validateUpdate(category);
			return categoryDAO.update(category);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryServiceInterface#delete(com.gowtham.model.
	 * Category)
	 */
	@Override
	public int delete(Category category) throws ServiceException {
		try {
			categoryValidator.validateDelete(category);
			return categoryDAO.delete(category.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Delete", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.CategoryServiceInterface#findAll()
	 */
	@Override
	public void findAll() {
		categoryDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryServiceInterface#addCategory(com.gowtham.
	 * model.Category)
	 */
	@Override
	public int addCategory(Category category) throws ServiceException {
		try {
			categoryValidator.validateSave(category);
			if (categoryDAO.isPresent(category.getUser().getId(), category.getName())) {
				throw new ServiceException("category already exists");
			}
			return categoryDAO.save(category);
		} catch (ValidationException e) {
			throw new ServiceException("unable to save", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.CategoryServiceInterface#getCategory(java.lang.
	 * Integer)
	 */
	@Override
	public List<Category> getCategory(Integer id) {
		return categoryDAO.listByUserId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.CategoryServiceInterface#listByCategory(java.lang.
	 * String)
	 */
	@Override
	public List<Article> listByCategory(String categoryName) {
		CategoryDAOInterface categoryDAO = new CategoryDAO();
		return categoryDAO.listByCategory(categoryName);
	}

}
