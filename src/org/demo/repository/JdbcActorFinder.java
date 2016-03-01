package org.demo.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.demo.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 * 14.2.2 NamedParameterJdbcTemplate
 * 
 * support for programming JDBC statements using named parameters
 * delegates to the wrapped JdbcTemplate to do much of its work.
 * 
 * @author yangp
 * 2014年9月21日 上午8:22:49
 */
@Repository
public class JdbcActorFinder implements ActorFinder {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// 14.5.1 Inserting data using SimpleJdbcInsert
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    
	    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("ACTOR");
	}
	
	@Override
	public int countOfActorsByFirstName(String firstName) {

	    String sql = "select count(*) from ACTOR where first_name = :first_name";

//	    SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", firstName);
	    
	    //  using the Map-based style
	    Map<String, String> namedParameters = Collections.singletonMap("first_name", firstName);

	    return (Integer) this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
	}
	
	@Override
	public void save(Actor actor) {
		Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("id", actor.getId());
        parameters.put("first_name", actor.getFirstName());
        parameters.put("description", actor.getDescription());
        simpleJdbcInsert.execute(parameters);
		
	}
	
	public void saveAdvance(Actor actor) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(actor);
        simpleJdbcInsert.execute(parameters);
	}
	
}
