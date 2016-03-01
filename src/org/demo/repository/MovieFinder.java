package org.demo.repository;

import java.util.List;

import org.demo.domain.Product;

public interface MovieFinder {

	public void save(Product p);

	public void update(Product p);

	public void delete(Product p);

	/**
	 * 14.2.4 Executing statements
	 * 直接执行sql statement
	 * 
	 * 2014年9月21日 上午8:49:18
	 */
	public void doExecute();

	/**
	 * 14.4.1 Basic batch operations with the JdbcTemplate
	 * 
	 * 2014年9月21日 上午9:13:59
	 * @param products
	 */
	public void batchUpdate(List<Product> products);

	public List<Product> getProducts();

}
