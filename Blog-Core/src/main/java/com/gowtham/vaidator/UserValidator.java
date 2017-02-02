package com.gowtham.vaidator;

import com.gowtham.exception.ValidationException;
import com.gowtham.model.User;
import com.gowtham.util.ValidationUtil;

public class UserValidator {
	private void validateUser(User user) throws ValidationException {
		ValidationUtil.isNotValid(user,"Invalid User");
	}
	
	private void validateId(Integer id)throws ValidationException{
		ValidationUtil.isNotValid(id,"Invalid Id");
	}
	
	private void validateName(String name)throws ValidationException{
		ValidationUtil.isNotValid(name,"Invalid Name");
	}
	
	private void validateUserName(String username)throws ValidationException{
		ValidationUtil.isNotValid(username,"Invalid UserName");
	}
	
	private void validatePassword(String password)throws ValidationException{
		ValidationUtil.isNotValid(password,"Invalid Password");
	}
	
	private void validateEmailId(String emailId)throws ValidationException{
		ValidationUtil.isNotValid(emailId,"Invalid EmailID");
	}
	
	private void validatePhoneNo(String phoneNo)throws ValidationException{
		ValidationUtil.isNotValid(phoneNo,"Invalid Phone Number");
	}
	
	public void validateSave(User user) throws ValidationException{
		validateUser(user);
		validateName(user.getName());
		validateUserName(user.getUserName());
		validatePassword(user.getPassword());
		validateEmailId(user.getEmailId());
		validatePhoneNo(user.getPhoneNumber());
	}
	
	public void validateUpdate(User user) throws ValidationException{
		validateUser(user);
		validateId(user.getId());
		validatePassword(user.getPassword());
	}
	
	public void validateDelete(User user) throws ValidationException{
		validateUser(user);
		validateId(user.getId());
	}
}
