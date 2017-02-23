package com.gowtham.config;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan( basePackages="com.gowtham")
public class AppConfig {

	@Bean
	public DataSource getDataSource() {
		final BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("admin");
		ds.setUrl("jdbc:mysql://localhost:3306/Blog");
		return ds;
	}

	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		final JdbcTemplate jt = new JdbcTemplate();
		jt.setDataSource(getDataSource());
		return jt;
	}
}