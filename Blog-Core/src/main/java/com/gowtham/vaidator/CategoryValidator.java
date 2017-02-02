package com.gowtham.vaidator;

import com.gowtham.exception.ValidationException;
import com.gowtham.model.Category;
import com.gowtham.util.ValidationUtil;

public class CategoryValidator {
	
	private void validateCategory(Category category)throws ValidationException{
		ValidationUtil.isNotValid(category,"Invalid category");
	}
	private void validateId(Integer id)throws ValidationException{
		ValidationUtil.isNotValid(id,"Invalid Id");
	}
	

	private void validateName(String name)throws ValidationException{
		ValidationUtil.isNotValid(name,"Invalid Name");
	}
	
	private void validateUserId(Integer userId)throws ValidationException{
		ValidationUtil.isNotValid(userId,"Invalid UserId");
	}
	
	
	public void validateSave(Category category) throws ValidationException{
		validateCategory(category);
		validateUserId(category.getUser().getId());
		validateName(category.getName());
		validateUserId(category.getUser().getId());
	}
	
	public void validateUpdate(Category category) throws ValidationException{
		validateCategory(category);
		validateId(category.getId());
		validateName(category.getName());
	}
	
	public void validateDelete(Category category) throws ValidationException{
		validateCategory(category);
		validateId(category.getId());
	}
}
