package com.za.tutorial.databaseHandle;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExecuteQuery {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public List<Map<String, Object>> executeSelectQuery(String sql) {
		return this.jdbcTemplate.queryForList(sql);
	}
	
	public List<Object> executeUpdateQuery() {
		return this.jdbcTemplate.update( "select first_name, last_name from t_actor", new ActorMapper());
	}
}
