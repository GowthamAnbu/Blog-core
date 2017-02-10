package com.gowtham.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gowtham.dao.ArticleDAO;
import com.gowtham.dao.CategoryDAO;
import com.gowtham.dao.CategoryDetailDAO;
import com.gowtham.dao.UserDAO;
import com.gowtham.exception.ServiceException;
import com.gowtham.exception.ValidationException;
import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.CategoryDetail;
import com.gowtham.model.User;
import com.gowtham.validator.ArticleValidator;
import com.gowtham.validator.UserValidator;

public class ArticleService {
	final ArticleValidator articleValidator = new ArticleValidator();
	final ArticleDAO articleDAO = new ArticleDAO();
	final CategoryService categoryService = new CategoryService();
	final CategoryDetailService categoryDetailService = new CategoryDetailService();
	final UserService userService = new UserService();
	final UserValidator userValidator = new UserValidator();
	final UserDAO userDAO = new UserDAO();
	final CategoryDAO categoryDAO = new CategoryDAO();
	final CategoryDetailDAO categoryDetailDAO = new CategoryDetailDAO();
	final CategoryDetail categoryDetail = new CategoryDetail();

	@Transactional
	public String post(Article article, Category category, CategoryDetail categoryDetail) throws ServiceException {
		try {
			Integer userId=userDAO.getUserId(article.getUser().getName());
			if(userId==null){
				throw new ServiceException("Invalid User Name");
			}
			article.getUser().setId(userId);
			save(article);
			category.getUser().setId(userId);
			if(categoryDAO.isPresent(category.getUser().getId(), category.getName())){
				throw new ServiceException("Category already exists");
			}
			categoryService.save(category);
			if(!categoryDAO.isPresent(category.getUser().getId(), category.getName())){
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

	public int save(Article article) throws ServiceException {
		try {
			articleValidator.validateSave(article);
			return articleDAO.save(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to save", e);
		}
	}

	public int update(Article article) throws ServiceException {
		try {
			articleValidator.validateUpdate(article);
			return articleDAO.update(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}

	public void delete(Integer id){
			articleDAO.delete(id);
	}

	public List<Article> findAll() {
		return articleDAO.findAll();
	}

	public List<Article> viewAllArticle(User user) throws ServiceException {
		try {
			if(user.getId()==null){
				throw new ServiceException("Invalid User Name");
			}
			return articleDAO.viewAll(user.getId());
		}
		catch (ServiceException e) {
			throw new ServiceException("Unable to View", e);
		}
	}

	public List<Article> viewAllArticle() throws ServiceException {
			return articleDAO.viewAll();
	}
	
	public int updateArticle(Article article) throws ServiceException {
		return articleDAO.update(article);
	}

	public int deleteArticle(Article article) throws ServiceException {
		final Integer userId = userDAO.getUserId(article.getUser().getUserName());
		final Integer articleId = articleDAO.getArticleId(article.getName());
		try {
			if (userService.login(article.getUser()) == 1) {
				if (articleDAO.viewAllCheck(userId) == null) {
					throw new ServiceException("No Articles found");
				}
				else if (articleId == null) {
					throw new ServiceException("No Articles found for the name:" + article.getName());
				} 
				article.setId(articleId);
				article.getUser().setId(userId);
				articleValidator.validateDelete(article);
			}
			return articleDAO.delete(article.getId());
		}
		catch (ValidationException e) {
			throw new ServiceException("Unable to Update", e);
		}
	}
	
	public int viewArticle(CategoryDetail categoryDetail)throws ServiceException{
	     final Integer articleId=categoryDetailDAO.getArticleId(categoryDetail.getId());
		try{
		if(articleId==null){
			throw new ServiceException("Invalid category Name");
		}
		articleDAO.viewArticle(articleId);
		}
		catch(ServiceException e){
			throw new ServiceException("cannot view Article",e);
		}
		return 0;
	}
	
	public int publishSave(Article article) throws ServiceException {
		UserDAO userDAO = new UserDAO();
//		article.getUser().setId(userDAO.getUserId(article.getUser().getUserName()));
		try {
			if(articleDAO.isPresent(article.getUser().getUserName(), article.getName())){
				throw new ServiceException("title already exists");
			}
			articleValidator.validateSave(article);
			return articleDAO.save(article);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to save", e);
		}
	}
	
	
}
