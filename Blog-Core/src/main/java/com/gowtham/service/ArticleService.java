package com.gowtham.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gowtham.dao.ArticleDAO;
import com.gowtham.dao.ArticleDAOInterface;
import com.gowtham.dao.CategoryDAO;
import com.gowtham.dao.CategoryDAOInterface;
import com.gowtham.dao.CategoryDetailDAO;
import com.gowtham.dao.CategoryDetailDAOInterface;
import com.gowtham.dao.UserDAO;
import com.gowtham.dao.UserDAOInterface;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.CategoryDetail;
import com.gowtham.model.User;
import com.gowtham.validator.ArticleValidator;
import com.gowtham.validator.UserValidator;

public class ArticleService implements ArticleServiceInterface {
	final ArticleValidator articleValidator = new ArticleValidator();
	final ArticleDAOInterface articleDAO = new ArticleDAO();
	final CategoryServiceInterface categoryService = new CategoryService();
	final CategoryDetailServiceInterface categoryDetailService = new CategoryDetailService();
	final UserServiceInterface userService = new UserService();
	final UserValidator userValidator = new UserValidator();
	final UserDAOInterface userDAO = new UserDAO();
	final CategoryDAOInterface categoryDAO = new CategoryDAO();
	final CategoryDetailDAOInterface categoryDetailDAO = new CategoryDetailDAO();
	final CategoryDetail categoryDetail = new CategoryDetail();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.ArticleServiceInterface#post(com.gowtham.model.
	 * Article, com.gowtham.model.Category, com.gowtham.model.CategoryDetail)
	 */
	@Override
	@Transactional
	public String post(Article article, Category category, CategoryDetail categoryDetail) throws ServiceException {
		try {
			Integer userId = userDAO.getUserId(article.getUser().getName());
			if (userId == null) {
				throw new ServiceException("Invalid User Name");
			}
			article.getUser().setId(userId);
			save(article);
			category.getUser().setId(userId);
			if (categoryDAO.isPresent(category.getUser().getId(), category.getName())) {
				throw new ServiceException("Category already exists");
			}
			categoryService.save(category);
			if (!categoryDAO.isPresent(category.getUser().getId(), category.getName())) {
				throw new ServiceException("Category not exists");
			}
			categoryDetail.getCategory().setId(categoryDAO.getCategoryId(category.getName()));
			categoryDetail.getArticle().setId(articleDAO.getArticleId(article.getName()));
			categoryDetailService.save(categoryDetail);
		} catch (ServiceException e) {
			throw new ServiceException("Unable to Post", e);
		}
		return "POSTED SUCCESSFULLY";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.ArticleServiceInterface#save(com.gowtham.model.
	 * Article)
	 */
	@Override
	public int save(Article article) throws ServiceException {
		try {
			articleValidator.validateSave(article);
			return articleDAO.save(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to save", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#update(com.gowtham.model.
	 * Article)
	 */
	@Override
	public int update(Article article) throws ServiceException {
		try {
			articleValidator.validateUpdate(article);
			return articleDAO.update(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		articleDAO.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.ArticleServiceInterface#findAll()
	 */
	@Override
	public List<Article> findAll() {
		return articleDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#viewAllArticle(com.gowtham.
	 * model.User)
	 */
	@Override
	public List<Article> viewAllArticle(User user) throws ServiceException {
		try {
			if (user.getId() == null) {
				throw new ServiceException("Invalid User Name");
			}
			return articleDAO.viewAll(user);
		} catch (ServiceException e) {
			throw new ServiceException("Unable to View", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.service.ArticleServiceInterface#viewAllArticle()
	 */
	@Override
	public List<Article> viewAllArticle() throws ServiceException {
		return articleDAO.viewAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#updateArticle(com.gowtham.
	 * model.Article)
	 */
	@Override
	public int updateArticle(Article article) throws ServiceException {
		return articleDAO.update(article);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#deleteArticle(com.gowtham.
	 * model.Article)
	 */
	@Override
	public int deleteArticle(Article article) throws ServiceException {
		final Integer userId = userDAO.getUserId(article.getUser().getUserName());
		final Integer articleId = articleDAO.getArticleId(article.getName());
		try {
			if (userService.login(article.getUser()) == 1) {
				if (articleDAO.viewAllCheck(userId) == null) {
					throw new ServiceException("No Articles found");
				} else if (articleId == null) {
					throw new ServiceException("No Articles found for the name:" + article.getName());
				}
				article.setId(articleId);
				article.getUser().setId(userId);
				articleValidator.validateDelete(article);
			}
			return articleDAO.delete(article.getId());
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#viewArticle(com.gowtham.model
	 * .CategoryDetail)
	 */
	@Override
	public int viewArticle(CategoryDetail categoryDetail) throws ServiceException {
		final Integer articleId = categoryDetailDAO.getArticleId(categoryDetail.getId());
		try {
			if (articleId == null) {
				throw new ServiceException("Invalid category Name");
			}
			articleDAO.viewArticle(articleId);
		} catch (ServiceException e) {
			throw new ServiceException("cannot view Article", e);
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#publishSave(com.gowtham.model
	 * .Article)
	 */
	@Override
	public int publishSave(Article article) throws ServiceException {
		try {
			if (articleDAO.isPresent(article.getUser().getUserName(), article.getName())) {
				throw new ServiceException("title already exists");
			}
			articleValidator.validateSave(article);
			return articleDAO.save(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to save", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.service.ArticleServiceInterface#getUserId(java.lang.Integer)
	 */
	@Override
	public Integer getUserId(Integer articleId) {
		ArticleDAOInterface articleDAO = new ArticleDAO();
		return articleDAO.getUserId(articleId);
	}
}
