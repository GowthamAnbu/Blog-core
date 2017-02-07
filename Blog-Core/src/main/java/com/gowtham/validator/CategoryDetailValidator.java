package com.gowtham.validator;

import com.gowtham.exception.ValidationException;
import com.gowtham.model.CategoryDetail;
import com.gowtham.util.ValidationUtil;

public class CategoryDetailValidator {
	
	private void validateCategoryDetail(CategoryDetail categoryDetail)throws ValidationException{
		ValidationUtil.isNotValid(categoryDetail,"Invalid category");
	}
	
	private void validateId(Integer id)throws ValidationException{
		ValidationUtil.isNotValid(id,"Invalid Id");
	}
	
	private void validateArticleId(Integer articleId)throws ValidationException{
		ValidationUtil.isNotValid(articleId,"Invalid ArticleId");
	}
	
	private void validateCategoryId(Integer categoryId)throws ValidationException{
		ValidationUtil.isNotValid(categoryId,"Invalid CategoryId");
	}
	
	
	public void validateSave(CategoryDetail categoryDetail) throws ValidationException{
		validateCategoryDetail(categoryDetail);
		validateId(categoryDetail.getId());
		validateArticleId(categoryDetail.getArticle().getId());
		validateCategoryId(categoryDetail.getCategory().getId());
	}
	
	public void validateUpdate(CategoryDetail categoryDetail) throws ValidationException{
		validateCategoryDetail(categoryDetail);
		validateId(categoryDetail.getId());
		validateArticleId(categoryDetail.getArticle().getId());
	}
	
	public void validateDelete(CategoryDetail categoryDetail) throws ValidationException{
		validateCategoryDetail(categoryDetail);
		validateId(categoryDetail.getId());
	}
	
	public void validateUpdateCategory(CategoryDetail categoryDetail) throws ValidationException{
		validateCategoryDetail(categoryDetail);
		validateId(categoryDetail.getCategory().getId());
		validateArticleId(categoryDetail.getArticle().getId());
	}
	
}

