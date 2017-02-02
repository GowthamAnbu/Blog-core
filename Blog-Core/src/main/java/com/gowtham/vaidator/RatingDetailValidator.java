package com.gowtham.vaidator;

import com.gowtham.exception.ValidationException;
import com.gowtham.model.RatingDetail;
import com.gowtham.util.ValidationUtil;

public class RatingDetailValidator {
	
	private void validateRatingDetail(RatingDetail ratingDetail)throws ValidationException{
		ValidationUtil.isNotValid(ratingDetail,"Invalid category");
	}
	
	private void validateId(Integer id)throws ValidationException{
		ValidationUtil.isNotValid(id,"Invalid Id");
	}
	
	private void validateArticleId(Integer articleId)throws ValidationException{
		ValidationUtil.isNotValid(articleId,"Invalid ArticleId");
	}
	
	private void validateUserId(Integer userId)throws ValidationException{
		ValidationUtil.isNotValid(userId,"Invalid UserId");
	}
	
	private void validateRating(Integer rating)throws ValidationException{
		ValidationUtil.isNotValid(rating,"Invalid Comment");
	}
	
	public void validateSave(RatingDetail ratingDetail) throws ValidationException{
		validateRatingDetail(ratingDetail);
		validateId(ratingDetail.getId());
		validateArticleId(ratingDetail.getArticle().getId());
		validateUserId(ratingDetail.getUser().getId());
		validateRating(ratingDetail.getRating());
	}
	
	public void validateUpdate(RatingDetail ratingDetail) throws ValidationException{
		validateRatingDetail(ratingDetail);
		validateId(ratingDetail.getId());
		validateArticleId(ratingDetail.getArticle().getId());
	}
	
	public void validateDelete(RatingDetail ratingDetail) throws ValidationException{
		validateRatingDetail(ratingDetail);
		validateId(ratingDetail.getId());
	}
	
}
