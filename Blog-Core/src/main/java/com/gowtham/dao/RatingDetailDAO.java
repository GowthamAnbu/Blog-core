package com.gowtham.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gowtham.model.Article;
import com.gowtham.model.RatingDetail;
import com.gowtham.model.User;

@Repository
public class RatingDetailDAO implements RatingDetailDAOInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.RatingDetailDAOInterface#save(com.gowtham.model.
	 * RatingDetail)
	 */
	@Override
	public int save(RatingDetail ratingDetail) {
		String sql = "INSERT INTO RATINGS_DETAILS (ID,ARTICLE_ID,USER_ID,RATINGS) VALUES(?,?,?,?)";
		Object[] args = { ratingDetail.getId(), ratingDetail.getArticle().getId(), ratingDetail.getUser().getId(),
				ratingDetail.getRating() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.RatingDetailDAOInterface#update(com.gowtham.model.
	 * RatingDetail)
	 */
	@Override
	public int update(RatingDetail ratingDetail) {
		String sql = "UPDATE RATINGS_DETAILS SET ARTICLE_ID=? WHERE ID=?";
		Object[] args = { ratingDetail.getArticle().getId(), ratingDetail.getId() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.RatingDetailDAOInterface#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM RATINGS_DETAILS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.RatingDetailDAOInterface#findAll()
	 */
	@Override
	public List<RatingDetail> findAll() {
		String sql = "SELECT ID,ARTICLE_ID,USER_ID,RATINGS FROM RATINGS_DETAILS";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.RatingDetailDAOInterface#findOne(java.lang.Integer)
	 */
	@Override
	public RatingDetail findOne(Integer id) {
		String sql = "SELECT ID,ARTICLE_ID,USER_ID,RATINGS FROM RATINGS_DETAILS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final RatingDetail ratingDetail = new RatingDetail();
			ratingDetail.setId(rs.getInt("ID"));
			final Article a = new Article();
			a.setId(rs.getInt("ARTICLE_ID"));
			ratingDetail.setArticle(a);
			final User u = new User();
			u.setId(rs.getInt("USER_ID"));
			ratingDetail.setRating(rs.getInt("RATINGS"));
			return ratingDetail;
		});
	}

}
