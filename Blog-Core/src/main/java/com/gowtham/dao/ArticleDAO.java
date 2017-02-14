package com.gowtham.dao;

import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.gowtham.model.Article;
import com.gowtham.model.User;
import com.gowtham.util.ConnectionUtil;

public class ArticleDAO implements DAO<Article> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public int save(Article article) {
		String sql = "INSERT INTO ARTICLES(USER_ID,NAME,CONTENT,MODIFIED_DATE) VALUES(?,?,?,?)";
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		SimpleDateFormat noMilliSecondsFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		noMilliSecondsFormatter.format(timestamp);
		article.setModifiedDate(timestamp.toLocalDateTime());
		Object[] args = { article.getUser().getId(), article.getName(), article.getContent(),
				article.getModifiedDate() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int update(Article article) {
		String sql = "UPDATE ARTICLES SET CONTENT=?,NAME=? WHERE ID=?";
		Object[] args = { article.getContent(), article.getName(), article.getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(Integer id) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
		call.withProcedureName("DELETE_ARTICLE");
		call.declareParameters(new SqlParameter("lid", Types.INTEGER),
				new SqlOutParameter("result", Types.INTEGER));
		call.setAccessCallParameterMetaData(false);
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("lid", id);
		Map<String, Object> execute = call.execute(in);
		return (int) execute.get("result");
		 
	}

	@Override
	public List<Article> findAll() {
		String sql = "SELECT ID,USER_ID,NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final Article article = new Article();
			article.setId(rs.getInt("ID"));
			final User user = new User();
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
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final Article article = new Article();
			article.setId(rs.getInt("ID"));
			final User u = new User();
			u.setId(rs.getInt("USER_ID"));
			article.setUser(u);
			article.setName(rs.getString("NAME"));
			article.setContent(rs.getString("CONTENT"));
			article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
			article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
			return article;
		});
	}

	public List<Article> viewAll(Integer id) {
		String sql = "SELECT ID,USER_ID,NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES WHERE USER_ID=?";
		Object[] args = { id };
		return jdbcTemplate.query(sql, args, (rs, rowNum) -> {
			final Article article = new Article();
			article.setId(rs.getInt("ID"));
			final User u = new User();
			u.setId(rs.getInt("USER_ID"));
			article.setUser(u);
			article.setName(rs.getString("NAME"));
			article.setContent(rs.getString("CONTENT"));
			article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
			article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
			return article;
		});
	}

	public List<Article> viewAll() {
		String sql = "SELECT ID,USER_ID,NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final Article article = new Article();
			article.setId(rs.getInt("ID"));
			final User u = new User();
			u.setId(rs.getInt("USER_ID"));
			article.setUser(u);
			article.setName(rs.getString("NAME"));
			article.setContent(rs.getString("CONTENT"));
			article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
			article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
			return article;
		});
	}

	public Integer viewAllCheck(Integer userId) {
		String sql = "SELECT IFNULL((SELECT 1 FROM ARTICLES WHERE USER_ID=?),NULL)";
		Object[] args = { userId };
		return jdbcTemplate.queryForObject(sql, args, int.class);
	}

	public Integer getArticleId(String articleName) {
		String sql = "SELECT IFNULL((SELECT ID FROM ARTICLES WHERE NAME=?),NULL)";
		Object[] args = { articleName };
		return jdbcTemplate.queryForObject(sql, args, int.class);
	}

	public Article viewArticle(Integer id) {
		String sql = "SELECT ID,USER_ID,NAME,CONTENT,PUBLISHED_DATE,MODIFIED_DATE FROM ARTICLES WHERE ID=?";
		Object[] args = { id };
		return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
			final Article article = new Article();
			article.setId(rs.getInt("ID"));
			final User u = new User();
			u.setId(rs.getInt("USER_ID"));
			article.setUser(u);
			article.setName(rs.getString("NAME"));
			article.setContent(rs.getString("CONTENT"));
			article.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE").toLocalDateTime());
			article.setModifiedDate(rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
			return article;
		});
	}

	public int updateArticle(Article article) {
		String sql = "UPDATE ARTICLES SET TITLE=? , CONTENT=? WHERE ID=?";
		Object[] args = { article.getName(), article.getContent(), article.getId() };
		return jdbcTemplate.update(sql, args);
	}

	public Boolean isPresent(String userName, String articleName) {
		UserDAO userDAO = new UserDAO();
		final Integer userId = userDAO.getUserId(userName);
		final String sql = "SELECT IFNULL((select TRUE FROM ARTICLES WHERE NAME=? AND USER_ID=?),FALSE)";
		Object[] args = { articleName, userId };
		return jdbcTemplate.queryForObject(sql, args, boolean.class);
	}
	
	public Integer getUserId(Integer articleId){
		String sql="SELECT USER_ID FROM ARTICLES WHERE ARTICLE_ID=?";
		Object[] args={articleId};
		return jdbcTemplate.queryForObject(sql, args,Integer.class);
	}
	
}
