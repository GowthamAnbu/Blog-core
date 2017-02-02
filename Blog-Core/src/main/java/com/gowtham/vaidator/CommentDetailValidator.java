package com.gowtham.vaidator;

import com.gowtham.exception.ValidationException;
import com.gowtham.model.CommentDetail;
import com.gowtham.util.ValidationUtil;

public class CommentDetailValidator {

	private void validateCommentDetail(CommentDetail commentDetail)throws ValidationException{
		ValidationUtil.isNotValid(commentDetail,"Invalid category");
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
	
	private void validateComment(String comment)throws ValidationException{
		ValidationUtil.isNotValid(comment,"Invalid Comment");
	}
	
	public void validateSave(CommentDetail commentDetail) throws ValidationException{
		validateCommentDetail(commentDetail);
		validateId(commentDetail.getId());
		validateArticleId(commentDetail.getArticle().getId());
		validateUserId(commentDetail.getUser().getId());
		validateComment(commentDetail.getComment());
	}
	
	public void validateUpdate(CommentDetail commentDetail) throws ValidationException{
		validateCommentDetail(commentDetail);
		validateId(commentDetail.getId());
		validateArticleId(commentDetail.getArticle().getId());
	}
	
	public void validateDelete(CommentDetail commentDetail) throws ValidationException{
		validateCommentDetail(commentDetail);
		validateId(commentDetail.getId());
	}
	
}
