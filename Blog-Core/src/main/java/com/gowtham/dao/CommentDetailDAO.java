package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Article;
import com.gowtham.model.CommentDetail;
import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class CommentDetailDAO implements DAO<CommentDetail>{
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	@Override
	public int save(CommentDetail commentDetail) {
		String sql = "INSERT INTO COMMENTS_DETAIL (ID,ARTICLE_ID,USER_ID,COMMENTS) VALUES(?,?,?,?)";
		Object[] args = { commentDetail.getId(), commentDetail.getArticle().getId(),
				commentDetail.getUser().getId(),commentDetail.getComment() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(CommentDetail commentDetail) {
		String sql = "UPDATE COMMENTS_DETAIL SET ARTICLE_ID=? WHERE ID=?";
		Object[] args = {commentDetail.getArticle().getId(),commentDetail.getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM COMMENTS_DETAIL WHERE ID=?";
		Object[] args = {id};
		return jdbcTemplate.update(sql, args);
	}

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

	@Override
	public CommentDetail findOne(Integer id) {
		String sql = "SELECT ID,ARTICLE_ID,USER_ID,COMMENTS FROM CATEGORY_DETAILS WHERE ID=?";
		Object[] args={id};
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
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

}
