package com.gowtham.util;

import java.time.LocalDateTime;

import com.gowtham.exception.ValidationException;

public class ValidationUtil {
	private ValidationUtil(){
		
	}
	public static boolean isValid(Object obj){
		return obj!=null;
	}
	
	public static void isNotValid(Object object,String message)throws ValidationException{
		if(object==null){
			throw new ValidationException(message);
		}
	}
	
	public static boolean isValid(Integer number){
		return number!=null && number >0;
	}
	
	public static void isNotValid(Integer number,String message)throws ValidationException{
		if(number==null || number.intValue()<=0){
			throw new ValidationException(message);
		}
	}
	
	public static boolean isValid(String string) {
		return string != null && !"".equals(string.trim());
	}
	
	public static void isNotValid(String string,String message) throws ValidationException{
		if(string == null || "".equals(string.trim())){
			throw new ValidationException(message);
		}
	}
	
	public static boolean isValid(Boolean boolnumber){
		return boolnumber!=null;
	}
	
	public static void isNotValid(Boolean boolnumber,String message)throws ValidationException{
		if(boolnumber==null){
			throw new ValidationException(message);
		}
	}
	
	public static boolean isValid(LocalDateTime time){
		return time!=null;
	}
	
	public static void isNotValid(LocalDateTime time,String message)throws ValidationException{
		if(time==null){
			throw new ValidationException(message);
		}
	}
	
}
