package com.gowtham.model;

import lombok.Data;

@Data
public class User {
	
private Integer id;
private Role role;
private String name;
private String userName;
private String password;
private String emailId;
private String phoneNumber;

}
