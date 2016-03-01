package org.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.domain.Product;
import org.demo.repository.ActorFinder;
import org.demo.repository.MovieFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnotationManager implements MovieManager {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private MovieFinder movieFinder;
	
	@Autowired
	private ActorFinder actorFinder;
	
	/**
	 * In proxy mode, only external method calls coming in through the proxy are intercepted. 
	 * This means that self-invocation, in effect, a method within the target object calling another method of the target object, will not lead to an actual transaction at runtime even if the invoked method is marked with @Transactional.
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void saveBean() {
		Product p = new Product();
    	p.setDescription("saveBeanZZ");
    	p.setId(4);
    	
//    	movieFinder.save(p);
    	movieFinder.update(p);
	}
	
	public void testSelfInvocation() {
		logger.info("testSelfInvocation");
	}
	
	public static void main(String[] args) {
		
		AnnotationManager m = new AnnotationManager();
		m.testSelfInvocation();
		
	}
}
