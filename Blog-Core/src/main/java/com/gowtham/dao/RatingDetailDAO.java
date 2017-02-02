package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Article;
import com.gowtham.model.RatingDetail;
import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class RatingDetailDAO implements DAO<RatingDetail>{
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	@Override
	public int save(RatingDetail ratingDetail) {
		String sql = "INSERT INTO RATINGS_DETAILS (ID,ARTICLE_ID,USER_ID,RATINGS) VALUES(?,?,?,?)";
		Object[] args = { ratingDetail.getId(), ratingDetail.getArticle().getId(),
				ratingDetail.getUser().getId(),ratingDetail.getRating()};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(RatingDetail ratingDetail) {
		String sql = "UPDATE RATINGS_DETAILS SET ARTICLE_ID=? WHERE ID=?";
		Object[] args = {ratingDetail.getArticle().getId(),ratingDetail.getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM RATINGS_DETAILS WHERE ID=?";
		Object[] args = {id};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<RatingDetail> findAll() {
		String sql = "SELECT ID,ARTICLE_ID,USER_ID,RATINGS FROM CATEGORY_DETAILS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
		final RatingDetail ratingDetail = new RatingDetail();
		ratingDetail.setId(rs.getInt("ID"));
		final Article article = new Article();
		article.setId(rs.getInt("ARTICLE_ID"));
		ratingDetail.setArticle(article);
		final User user = new User();
		user.setId(rs.getInt("USER_ID"));
		ratingDetail.setRating(rs.getInt("RATINGS"));
		return ratingDetail;
	});
	}

	@Override
	public RatingDetail findOne(Integer id) {
		String sql = "SELECT ID,ARTICLE_ID,USER_ID,RATINGS FROM CATEGORY_DETAILS WHERE ID=?";
		Object[] args={id};
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
		final RatingDetail ratingDetail = new RatingDetail();
		ratingDetail.setId(rs.getInt("ID"));
		final Article article = new Article();
		article.setId(rs.getInt("ARTICLE_ID"));
		ratingDetail.setArticle(article);
		final User user = new User();
		user.setId(rs.getInt("USER_ID"));
		ratingDetail.setRating(rs.getInt("RATINGS"));
		return ratingDetail;
	});
	}

}
