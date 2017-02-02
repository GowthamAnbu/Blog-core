package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Category;
import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class CategoryDAO implements DAO<Category>{
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public int save(Category category) {
		String sql="INSERT INTO CATEGORIES (ID,NAME,USER_ID) VALUES(?,?,?)";
		Object[] args={category.getId(),category.getName(),category.getUser().getId()};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(Category category) {
		String sql="UPDATE CATEGORIES SET NAME=? WHERE ID=?";
		Object[] args={category.getName(),category.getId()};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		String sql="DELETE FROM CATEGORIES WHERE ID=?";
		Object[] args={id};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<Category> findAll() {
		String sql = "SELECT ID,NAME,USER_ID FROM CATEGORIES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
		final Category category = new Category();
		category.setId(rs.getInt("ID"));
		category.setName(rs.getString("NAME"));
		final User user=new User();
		user.setId(rs.getInt("USER_ID"));
		category.setUser(user);
		return category;
	});
	}

	@Override
	public Category findOne(Integer id) {
		String sql = "SELECT ID,NAME,USER_ID FROM CATEGORIES WHERE ID=?";
		Object[] args={id};
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
		final Category category = new Category();
		category.setId(rs.getInt("ID"));
		category.setName(rs.getString("NAME"));
		final User user=new User();
		user.setId(rs.getInt("USER_ID"));
		category.setUser(user);
		return category;
	});
	}

}
