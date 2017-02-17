package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Article;
import com.gowtham.model.CommentDetail;
import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class CommentDetailDAO implements CommentDetailDAOInterface {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CommentDetailDAOInterface#save(com.gowtham.model.
	 * CommentDetail)
	 */
	@Override
	public int save(CommentDetail commentDetail) {
		String sql = "INSERT INTO COMMENTS_DETAILS (ARTICLE_ID,USER_ID,COMMENTS) VALUES(?,?,?)";
		Object[] args = { commentDetail.getArticle().getId(), commentDetail.getUser().getId(),
				commentDetail.getComment() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CommentDetailDAOInterface#update(com.gowtham.model.
	 * CommentDetail)
	 */
	@Override
	public int update(CommentDetail commentDetail) {
		String sql = "UPDATE COMMENTS_DETAILS SET ARTICLE_ID=? WHERE ID=?";
		Object[] args = { commentDetail.getArticle().getId(), commentDetail.getId() };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CommentDetailDAOInterface#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM COMMENTS_DETAILS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CommentDetailDAOInterface#findAll()
	 */
	@Override
	public List<CommentDetail> findAll() {
		String sql = "SELECT ID,ARTICLE_ID,USER_ID,COMMENTS FROM CATEGORY_DETAILS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final CommentDetail commentDetail = new CommentDetail();
			commentDetail.setId(rs.getInt("ID"));
			final Article article = new Article();
			article.setId(rs.getInt("ARTICLE_ID"));
			commentDetail.setArticle(article);
			final User user = new User();
			user.setId(rs.getInt("USER_ID"));
			commentDetail.setComment(rs.getString("COMMENTS"));
			return commentDetail;
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gowtham.dao.CommentDetailDAOInterface#findOne(java.lang.Integer)
	 */
	@Override
	public CommentDetail findOne(Integer id) {
		String sql = "SELECT ID,ARTICLE_ID,USER_ID,COMMENTS FROM CATEGORY_DETAILS WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final CommentDetail commentDetail = new CommentDetail();
			commentDetail.setId(rs.getInt("ID"));
			final Article a = new Article();
			a.setId(rs.getInt("ARTICLE_ID"));
			commentDetail.setArticle(a);
			final User u = new User();
			u.setId(rs.getInt("USER_ID"));
			commentDetail.setComment(rs.getString("COMMENTS"));
			return commentDetail;
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gowtham.dao.CommentDetailDAOInterface#getComments(java.lang.Integer)
	 */
	@Override
	public List<CommentDetail> getComments(Integer articleId) {
		String sql = "SELECT COMMENTS FROM  COMMENTS_DETAILS  WHERE ARTICLE_ID=?";
		Object[] args = { articleId };
		return jdbcTemplate.query(sql, args, (rs, rowNum) -> {
			final CommentDetail commentDetail = new CommentDetail();
			commentDetail.setComment(rs.getString("COMMENTS"));
			return commentDetail;
		});
	}

}
