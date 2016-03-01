package org.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.domain.Product;
import org.demo.repository.ActorFinder;
import org.demo.repository.MovieFinder;
import org.demo.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductManager implements ProductManager {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private MovieFinder movieFinder;
	
	@Autowired
	private ActorFinder actorFinder;

    public List<Product> getProducts() {
        return productDao.getProductList();
    }

    public void increasePrice(int percentage) {
        List<Product> products = productDao.getProductList();
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
                productDao.saveProduct(product);
            }
        }
    }
    
    @Override
    public void daoTest(){
  
    	/*for (int i = 5; i < 1000; i++) {
    		Actor p = new Actor();
        	p.setDescription("setDescription" + i);
        	p.setId(i);
        	p.setFirstName("firstName" + i);
        	this.actorFinder.saveAdvance(p);
		}*/
    	
    	List<Product> products = movieFinder.getProducts();
    	for (Product product : products) {
    		product.setDescription("setDescription" + Math.random());
		}
    	movieFinder.batchUpdate(products);
    	
    	/*List<Product> products = new ArrayList<Product>();
    	for (int i = 5; i < 1000; i++) {
    		Product p = new Product();
        	p.setDescription("setDescription" + i);
        	p.setId(i);
        	p.setPrice(Math.random());
        	products.add(p);
		}*/
    	
    	
//    	movieFinder.doExecute();
    	
//    	int count = actorFinder.countOfActorsByFirstName("xxx");
//    	logger.info(count);
    }
    
    @Override
    public void saveBean() {
    	
    	Product p = new Product();
    	p.setDescription("saveBean");
    	p.setId(4);
    	p.setPrice(2.02);
    	movieFinder.delete(p);
    	movieFinder.save(p);
//    	movieFinder.update(p);
    }
    
    @PostConstruct
    private void init() {
    	logger.info("@SimpleProductManager init...");
    }
    
    @PreDestroy
    private void destory() {
    	logger.info("@SimpleProductManager destory...");
    }

	@Override
	public void accessManager(String name, int age) {
		logger.info("accessManager");
		
	}

}
