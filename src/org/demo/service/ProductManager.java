package org.demo.service;

import java.util.List;

import org.demo.domain.Product;

public interface ProductManager {

	public void increasePrice(int percentage);
    
    public List<Product> getProducts();

    public void saveBean();

    /**
     * 测试Dao
     * 
     * 2014年9月21日 上午8:32:28
     */
    public void daoTest();
    
    public void accessManager(String name, int age);
    
}
