package org.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.demo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 13. DAO support
 * 
 * @author yangp
 * 2014年9月19日 下午11:29:38
 */
@Repository
public class JdbcMovieFinder implements MovieFinder {

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
	public List<Product> getProducts() {
	    List<Product> products = this.jdbcTemplate.query(
	            "select id, description, price from products",
	            new RowMapper() {
					
					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	                	Product p = new Product();
	                    p.setId(rs.getInt("id"));
	                    p.setDescription(rs.getString("description"));
	                    p.setPrice(rs.getDouble("price"));
	                    return p;
					}
				});
	    return products;
	};
    
	@Override
	public void save(Product p) {
		this.jdbcTemplate.update(
		        "insert into products (id, description, price) values (?, ?, ?)",
		        new Object[]{p.getId(), p.getDescription(), p.getPrice()},
		        new int[]{Types.INTEGER, Types.VARCHAR, Types.DOUBLE});
	}
	
	@Override
	public void delete(Product p) {
		this.jdbcTemplate.update("delete from products where id = " + p.getId());
	}
	
	@Override
	public void update(Product p) {
		this.jdbcTemplate.update(
		        "update products set description = ?, price = ? where id = ?",
		        new Object[]{p.getDescription(), p.getPrice(), p.getId()},
		        new int[]{Types.VARCHAR, Types.DOUBLE, Types.INTEGER});
	}
	
	@Override
	public void batchUpdate(final List<Product> products) {
		this.jdbcTemplate.batchUpdate(
				"update products set description = ?, price = ? where id = ?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, products.get(i).getDescription());
						ps.setDouble(2, products.get(i).getPrice());
						ps.setInt(3, products.get(i).getId());
					}
					
					@Override
					public int getBatchSize() {
						return products.size();
					}
				});
		
		
	}
	
	@Override
	public void doExecute() {
		this.jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");
		
		// instead of Java EE's standard DataSource.getConnection
//		Connection connection = DataSourceUtils.getConnection(this.jdbcTemplate.getDataSource());
		
	}

    // ...
    
}
