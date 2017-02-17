package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Article;
import com.gowtham.model.Category;
import com.gowtham.model.CategoryDetail;
import com.gowtham.util.ConnectionUtil;

public class CategoryDetailDAO implements CategoryDetailDAOInterface {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CategoryDetailDAOInterface#save(com.gowtham.model.
	 * CategoryDetail)
	 */
	@Override
	public int save(CategoryDetail categoryDetail) {
		String sql = "INSERT INTO CATEGORY_DETAILS (ARTICLE_ID,CATEGORY_ID) VALUES(?,?)";
		Object[] args = { categoryDetail.getArticle().getId(), categoryDetail.getCategory().getId() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CategoryDetailDAOInterface#update(com.gowtham.model.
	 * CategoryDetail)
	 */
	@Override
	public int update(CategoryDetail categoryDetail) {
		String sql = "UPDATE CATEGORY_DETAILS SET CATEGORY_ID=? WHERE ID=?";
		Object[] args = { categoryDetail.getCategory().getId(), categoryDetail.getId() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CategoryDetailDAOInterface#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM CATEGORY_DETAILS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CategoryDetailDAOInterface#findAll()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.dao.CategoryDetailDAOInterface#findOne(java.lang.Integer)
	 */
	@Override
	public CategoryDetail findOne(Integer id) {
		String sql = "SELECT ID,ARTICLE_ID,CATEGORY_ID FROM CATEGORY_DETAILS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.dao.CategoryDetailDAOInterface#updateCategory(com.gowtham.
	 * model.CategoryDetail)
	 */
	@Override
	public int updateCategory(CategoryDetail categoryDetail) {
		String sql = "UPDATE CATEGORY_DETAILS SET CATEGORY_ID=? WHERE ARTICLE_ID=?";
		Object[] args = { categoryDetail.getCategory().getId(), categoryDetail.getArticle().getId() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CategoryDetailDAOInterface#getArticleId(java.lang.
	 * Integer)
	 */
	@Override
	public Integer getArticleId(Integer id) {
		String sql = "SELECT IFNULL((SELECT ARTICLE_ID FROM CATEGORY_DETAILS WHERE CATEGORY_ID=?),NULL)";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

}
