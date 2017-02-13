package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class CategoryDAO implements DAO<Category> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public int save(Category category) {
		String sql = "INSERT INTO CATEGORIES (NAME,USER_ID) VALUES(?,?)";
		Object[] args = { category.getName(), category.getUser().getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(Category category) {
		String sql = "UPDATE CATEGORIES SET NAME=? WHERE ID=?";
		Object[] args = { category.getName(), category.getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM CATEGORIES WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<Category> findAll() {
		String sql = "SELECT ID,NAME,USER_ID FROM CATEGORIES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final Category category = new Category();
			category.setId(rs.getInt("ID"));
			category.setName(rs.getString("NAME"));
			final User user = new User();
			user.setId(rs.getInt("USER_ID"));
			category.setUser(user);
			return category;
		});
	}

	@Override
	public Category findOne(Integer id) {
		String sql = "SELECT ID,NAME,USER_ID FROM CATEGORIES WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final Category category = new Category();
			category.setId(rs.getInt("ID"));
			category.setName(rs.getString("NAME"));
			final User user = new User();
			user.setId(rs.getInt("USER_ID"));
			category.setUser(user);
			return category;
		});
	}

	public Integer getCategoryId(String categoryName) {
		String sql = "SELECT IFNULL((SELECT id FROM CATEGORIES WHERE NAME=?),NULL)";
		Object[] args = { categoryName };
		return jdbcTemplate.queryForObject(sql, args, int.class);
	}

	public boolean isPresent(Integer userId, String categoryName) {
		String sql = "SELECT IFNULL((SELECT TRUE FROM CATEGORIES WHERE USER_ID=? AND NAME=?),FALSE)";
		Object[] args = { userId, categoryName };
		return jdbcTemplate.queryForObject(sql, args, boolean.class);
	}

	public List<String> getCategory(Integer id) {
		String sql = "SELECT NAME FROM CATEGORIES WHERE USER_ID=?";
		Object[] args = { id };
		List<String> queryForList = jdbcTemplate.queryForList(sql, args, String.class);
		return queryForList;
	}

	public List<Category> listByUserId(int userId) {
		final String sql = "SELECT ID,NAME FROM CATEGORIES WHERE USER_ID=?";
		Object[] params = { userId };
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
			final Category category = new Category();
			category.setId(rs.getInt("ID"));
			category.setName(rs.getString("NAME"));
			return category;
		});
	}

	public List<Article> listByCategory(String categoryName) {
		final String sql = "SELECT NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES WHERE ID IN (SELECT ARTICLE_ID FROM CATEGORY_DETAILS WHERE CATEGORY_ID IN(SELECT ID FROM CATEGORIES WHERE NAME=?));";
		Object[] args={categoryName};
		return jdbcTemplate.query(sql,args,(rs,rowNum)->{
			final Article article=new Article();
			article.setName(rs.getString("NAME"));
			article.setContent(rs.getString("CONTENT"));
			article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
			article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
			return article;
		});
	}

}
