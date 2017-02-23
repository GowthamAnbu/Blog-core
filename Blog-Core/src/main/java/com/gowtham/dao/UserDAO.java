package com.gowtham.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gowtham.model.Role;
import com.gowtham.model.User;

@Repository
public class UserDAO implements UserDAOInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;//=ConnectionUtil.getJdbcTemplate();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#save(com.gowtham.model.User)
	 */
	@Override
	public int save(User user) {
		String sql = "INSERT INTO USERS (NAME,USER_NAME,PASSWORD,EMAIL_ID,PHONE_NUMBER) VALUES(?,?,?,?,?)";
		Object[] args = { user.getName(), user.getUserName(), user.getPassword(), user.getEmailId(),
				user.getPhoneNumber() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#update(com.gowtham.model.User)
	 */
	@Override
	public int update(User user) {
		String sql = "UPDATE USERS SET PASSWORD=? WHERE ID=?";
		Object[] args = { user.getPassword(), user.getId() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM USERS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#findAll()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#findOne(java.lang.Integer)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#isValidUserName(java.lang.String)
	 */
	@Override
	public Boolean isValidUserName(String name) {
		String sql = "SELECT ISVALID_USER_NAME(?)";
		Object[] args = { name };
		return jdbcTemplate.queryForObject(sql, args, boolean.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#isValidPassword(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean isValidPassword(String name, String password) {
		String sql = "SELECT PASSWORD FROM USERS WHERE USER_NAME=?";
		Object[] args = {name};
		String dbpassword = jdbcTemplate.queryForObject(sql, args,String.class);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password,dbpassword);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#getUserId(java.lang.String)
	 */
	@Override
	public Integer getUserId(String userName) {
		String sql = "SELECT IFNULL((SELECT ID FROM USERS WHERE USER_NAME=?),NULL)";
		Object[] args = { userName };
		return jdbcTemplate.queryForObject(sql, args, int.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#getUserName(java.lang.Integer)
	 */
	@Override
	public String getUserName(Integer userId) {
		String sql = "SELECT IFNULL((SELECT USER_NAME FROM USERS WHERE ID=?),NULL)";
		Object[] args = { userId };
		return jdbcTemplate.queryForObject(sql, args, String.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String userName) {
		String sql = "SELECT ID,ROLE_ID,NAME,USER_NAME,PASSWORD,EMAIL_ID,PHONE_NUMBER FROM USERS WHERE USER_NAME=?";
		Object[] args = { userName };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final User user = new User();
			user.setId(rs.getInt("ID"));
			final Role role = new Role();
			role.setId(rs.getInt("ROLE_ID"));
			user.setRole(role);
			user.setName(rs.getString("NAME"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setEmailId(rs.getString("EMAIL_ID"));
			user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			return user;
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#getRole(java.lang.Integer)
	 */
	@Override
	public Integer getRole(Integer id) {
		String sql = "SELECT ROLE_ID FROM USERS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#forAdmin()
	 */
	@Override
	public List<User> forAdmin() {
		String sql = "SELECT ID,NAME,USER_NAME,ROLE_ID,EMAIL_ID,PHONE_NUMBER FROM USERS WHERE ROLE_ID<>1";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final User user = new User();
			user.setId(rs.getInt("ID"));
			user.setName(rs.getString("NAME"));
			final Role role = new Role();
			role.setId(rs.getInt("ROLE_ID"));
			user.setRole(role);
			user.setUserName(rs.getString("USER_NAME"));
			user.setEmailId(rs.getString("EMAIL_ID"));
			user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			return user;
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#change(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public void change(Integer id, Integer roleId) {
		String sql = "UPDATE USERS SET ROLE_ID = ? WHERE ID=?";
		Object[] args = { roleId, id };
		jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.UserDAOInterface#getEmailId(java.lang.Integer)
	 */
	@Override
	public String getEmailId(Integer userId) {
		String sql = "SELECT EMAIL_ID FROM USERS WHERE ID=?";
		Object[] args = { userId };
		return jdbcTemplate.queryForObject(sql, args, String.class);
	}

}
