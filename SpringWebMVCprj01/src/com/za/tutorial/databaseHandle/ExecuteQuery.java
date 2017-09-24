package com.za.tutorial.databaseHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExecuteQuery{
	
	public static String ASCENDING = "ASC";
	public static String DESCENDING = "DESC";
	private ArrayList<Object> valuesToQuery;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
       // this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public List<Map<String, Object>> executeSelectQuery(String sql) {
		//return this.queryForList(sql, valuesToQuery);
		return null;
	}
		
	
	public List<Object> executeUpdateQuery() {
		//return this.jdbcTemplate.update( "select first_name, last_name from t_actor", new ActorMapper());
		return null;
	}
	
	/**
	 * Select methods
	 * 
	 */
	public List<Map<String, Object>> runSelect (String from) {
		return runSelect (from, null, null, true, null);
	}
	
	public List<Map<String, Object>> runSelect (String from, Integer limit) {
		return runSelect (from, null, null, true, limit);
	}
	
	public List<Map<String, Object>> runSelect (String from, ArrayList<SearchingObject> values, Integer limit) {
		return runSelect (from, values, null, true, limit);
	}
	
	public List<Map<String, Object>> runSelect (String from, ArrayList<SearchingObject> values, 
					String orderBy, Integer limit) {
		return runSelect (from, values, orderBy, true, limit);
	}
	
	public List<Map<String, Object>> runSelect (String from, ArrayList<SearchingObject> values, 
													String orderBy, boolean isAscending, Integer limit) {
		StringBuilder sql = new StringBuilder("SELECT * ");
		sql.append(from);
		sql.append(" ");
		sql.append(setWhereStatements(values));
		sql.append(setOrderBy(orderBy, isAscending));
		sql.append(setLimit(limit));
		sql.append(";");
		return executeSelectQuery(sql.toString());
	}
	
	public List<Map<String, Object>> runUpdate () {
		StringBuilder sql = new StringBuilder("UPDATE ");
		return executeSelectQuery(sql.toString());
	}
	
	public List<Map<String, Object>> runInsert () {
		StringBuilder sql = new StringBuilder("INSERT ");
		return executeSelectQuery(sql.toString());
	}
	
	public List<Map<String, Object>> runDelete () {
		StringBuilder sql = new StringBuilder("DELETE ");
		return executeSelectQuery(sql.toString());
	}
	
	private String setOrderBy (String orderBy, boolean isAscending) {
		StringBuilder sqlOrderBy = new StringBuilder();
		if(orderBy != null && !orderBy.isEmpty()) {
			sqlOrderBy.append(" ORDER BY ");
			sqlOrderBy.append(orderBy);
			sqlOrderBy.append(" ");
			sqlOrderBy.append(setDirection(isAscending));
		}
		
		return sqlOrderBy.toString();
	}
	
	private String setDirection(boolean isAscending) {	
		if(isAscending) {
			return ASCENDING;
		}else {
			return DESCENDING;
		}
	}
	
	private String setLimit (Integer limit) {
		StringBuilder sqlLimit = new StringBuilder();
		if (limit != null) {
			sqlLimit.append(" LIMIT ");
			sqlLimit.append(limit.intValue());
		}
		
		return sqlLimit.toString();
	}
	
	private String setWhereStatements(ArrayList<SearchingObject> values)	{
		StringBuilder sqlWhere = new StringBuilder();
		if(values != null && !values.isEmpty()) {
			sqlWhere.append(" WHERE ");			
			valuesToQuery = new ArrayList<>();
			for (SearchingObject value : values) {
				sqlWhere.append(value.getColumn());
				sqlWhere.append("=");
				sqlWhere.append("? ");
				valuesToQuery.add(value.getValue());
			}
		}
		return sqlWhere.toString();
	}
	
		
}
