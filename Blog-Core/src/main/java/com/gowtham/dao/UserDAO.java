package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class UserDAO implements DAO<User> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public int save(User user) {
		String sql = "INSERT INTO USERS (NAME,USER_NAME,PASSWORD,EMAIL_ID,PHONE_NUMBER) VALUES(?,?,?,?,?)";
		Object[] args = { user.getName(), user.getUserName(), user.getPassword(), user.getEmailId(),
				user.getPhoneNumber() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE USERS SET PASSWORD=? WHERE ID=?";
		Object[] args = { user.getPassword(), user.getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM USERS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT ID,NAME,USER_NAME,PASSWORD,EMAIL_ID,PHONE_NUMBER FROM USERS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final User user = new User();
			user.setId(rs.getInt("ID"));
			user.setName(rs.getString("NAME"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setEmailId(rs.getString("EMAIL_ID"));
			user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			return user;
		});
	}
	
	@Override
	public User findOne(Integer id) {
		String sql = "SELECT ID,NAME,USER_NAME,PASSWORD,EMAIL_ID,PHONE_NUMBER FROM USERS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final User user = new User();
			user.setId(rs.getInt("ID"));
			user.setName(rs.getString("NAME"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setEmailId(rs.getString("EMAIL_ID"));
			user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			return user;
		});
	}
	
	public Boolean isValidUserName(String name){
		String sql="SELECT ISVALID_USER_NAME(?)";
		Object[] args={name};
		return jdbcTemplate.queryForObject(sql,args, boolean.class);
	}
	
	public Boolean isValidPassword(String name,String password){
		String sql="SELECT ISVALID_PASSWORD(?,?)";
		Object[] args={name,password};
		return jdbcTemplate.queryForObject(sql,args, boolean.class);
	}
	
	public  Integer getUserId(String userName){
		String sql = "SELECT IFNULL((SELECT ID FROM USERS WHERE USER_NAME=?),NULL)";
		Object[] args={userName};
		 return jdbcTemplate.queryForObject(sql,args, int.class);
	}
	
	public  String getUserName(Integer userId){
		String sql = "SELECT IFNULL((SELECT USER_NAME FROM USERS WHERE ID=?),NULL)";
		Object[] args={userId};
		 return jdbcTemplate.queryForObject(sql,args, String.class);
	}
	
	public User getUser(String userName) {
		String sql = "SELECT ID,NAME,USER_NAME,PASSWORD,EMAIL_ID,PHONE_NUMBER FROM USERS WHERE USER_NAME=?";
		Object[] args = { userName };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final User user = new User();
			user.setId(rs.getInt("ID"));
			user.setName(rs.getString("NAME"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setEmailId(rs.getString("EMAIL_ID"));
			user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			return user;
		});
	}
	
}
