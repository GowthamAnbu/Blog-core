package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.CategoryDetail;
import com.gowtham.util.ConnectionUtil;

public class CategoryDetailDAO implements DAO<CategoryDetail> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public int save(CategoryDetail categoryDetail) {
		String sql = "INSERT INTO CATEGORY_DETAILS (ID,ARTICLE_ID,CATEGORY_ID) VALUES(?,?,?)";
		Object[] args = { categoryDetail.getId(), categoryDetail.getArticle().getId(),
				categoryDetail.getCategory().getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(CategoryDetail categoryDetail) {
		String sql = "UPDATE CATEGORY_DETAILS SET CATEGORY_ID=? WHERE ID=?";
		Object[] args = {categoryDetail.getCategory().getId(),categoryDetail.getId()};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM CATEGORY_DETAILS WHERE ID=?";
		Object[] args = {id};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<CategoryDetail> findAll() {
		String sql = "SELECT ID,ARTICLE_ID,CATEGORY_ID FROM CATEGORY_DETAILS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
		final CategoryDetail categoryDetail = new CategoryDetail();
		categoryDetail.setId(rs.getInt("ID"));
		final Article article = new Article();
		article.setId(rs.getInt("ARTICLE_ID"));
		categoryDetail.setArticle(article);
		final Category category = new Category();
		category.setId(rs.getInt("CATEGORY_ID"));
		categoryDetail.setCategory(category);
		return categoryDetail;
	});
	}

	@Override
	public CategoryDetail findOne(Integer id) {
		String sql = "SELECT ID,ARTICLE_ID,CATEGORY_ID FROM CATEGORY_DETAILS WHERE ID=?";
		Object[] args={id};
		return jdbcTemplate.queryForObject(sql,args , (rs, rowNum) -> {
		final CategoryDetail categoryDetail = new CategoryDetail();
		categoryDetail.setId(rs.getInt("ID"));
		final Article a = new Article();
		a.setId(rs.getInt("ARTICLE_ID"));
		categoryDetail.setArticle(a);
		final Category c = new Category();
		c.setId(rs.getInt("CATEGORY_ID"));
		categoryDetail.setCategory(c);
		return categoryDetail;
	});
	}

	public int updateCategory(CategoryDetail categoryDetail) {
		String sql = "UPDATE CATEGORY_DETAILS SET CATEGORY_ID=? WHERE ARTICLE_ID=?";
		Object[] args = {categoryDetail.getCategory().getId(),categoryDetail.getArticle().getId()};
		return jdbcTemplate.update(sql, args);
	}
	
}
