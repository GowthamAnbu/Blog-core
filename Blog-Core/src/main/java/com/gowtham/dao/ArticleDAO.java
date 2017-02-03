package com.gowtham.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gowtham.model.Article;
import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class ArticleDAO implements DAO<Article>{
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public int save(Article article) {
		String sql="INSERT INTO ARTICLES(USER_ID,NAME,CONTENT) VALUES(?,?,?,?,?)";
		Object[] args={article.getUser().getId(),article.getName(),article.getContent()};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(Article article) {
		String sql="UPDATE ARTICLES SET CONTENT=? WHERE ID=?";
		Object[] args={article.getContent(),article.getId()};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		String sql="DELETE FROM ARTICLES WHERE ID=?";
		Object[] args={id};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<Article> findAll() {
		String sql = "SELECT ID,USER_ID,NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
		final Article article =new Article();
		article.setId(rs.getInt("ID"));
		final User user=new User();
		user.setId(rs.getInt("USER_ID"));
		article.setUser(user);
		article.setName(rs.getString("NAME"));
		article.setContent(rs.getString("CONTENT"));
		article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
		article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
		return article;
	});
	}

	@Override
	public Article findOne(Integer id) {
		String sql = "SELECT ID,USER_ID,NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES WHERE ID=?";
		Object[] args={id};
		return jdbcTemplate.queryForObject(sql,args,(rs, rowNum) -> {
		final Article article =new Article();
		article.setId(rs.getInt("ID"));
		final User u=new User();
		u.setId(rs.getInt("USER_ID"));
		article.setUser(u);
		article.setName(rs.getString("NAME"));
		article.setContent(rs.getString("CONTENT"));
		article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
		article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
		return article;
	});
	}
	
	public Article viewAll(Integer id) {
		String sql = "SELECT ID,USER_ID,NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES WHERE USER_ID=?";
		Object[] args={id};
		return jdbcTemplate.queryForObject(sql,args,(rs, rowNum) -> {
		final Article article =new Article();
		article.setId(rs.getInt("ID"));
		final User u=new User();
		u.setId(rs.getInt("USER_ID"));
		article.setUser(u);
		article.setName(rs.getString("NAME"));
		article.setContent(rs.getString("CONTENT"));
		article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
		article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
		return article;
	});
	}
	
	public Integer viewAllCheck(Integer userId){
		String sql = "SELECT IFNULL((SELECT 1 FROM ARTICLES WHERE USER_ID=?),NULL)";
		Object[] args={userId};
		 return jdbcTemplate.queryForObject(sql,args, int.class);
	}
	
	public  Integer getArticleId(String articleName){
		String sql = "SELECT IFNULL((SELECT id FROM ARTICLES WHERE NAME=?),NULL)";
		Object[] args={articleName};
		 return jdbcTemplate.queryForObject(sql,args, int.class);
	}
	
}
