package org.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.context.ApplicationContextUtil;
import org.demo.context.ApplicationContextUtil2;
import org.demo.service.MovieManager;
import org.demo.service.NoInterface;
import org.demo.service.ProductManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class InstantiatingContainer {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private ApplicationContext context = null;
			
	@Before
    public void setUp() {
		context = new ClassPathXmlApplicationContext(new String[] {"apps.xml"});
    }
	
//	@Test
	public void testDataAccess () {
		ProductManager manager = (ProductManager)context.getBean("simpleProductManager");
		MovieManager manager_anno = (MovieManager)context.getBean("annotationManager");
		
		manager.saveBean();
		manager_anno.saveBean();
	}
	
	/**
	 * @Component + @Aspectj
	 * 
	 * 2015-5-4 下午09:05:31
	 */
	@Test
	public void testAnnotationAOP() {
		ProductManager manager = (ProductManager)context.getBean("simpleProductManager");
		manager.accessManager("xxx", 24);
	}
	
	/**
	 * 
	 * 
	 * 2015-5-4 下午09:02:05
	 */
	@Test
	public void testProxyType() {
		MovieManager manager_anno = (MovieManager)context.getBean("annotationManager");
		NoInterface noInterface = (NoInterface)context.getBean("noInterface");

		logger.info("isAopProxy(): " + AopUtils.isAopProxy(manager_anno));
		logger.info("isAopProxy(): " + AopUtils.isAopProxy(noInterface));
		logger.info("isJdkDynamicProxy(): " + AopUtils.isJdkDynamicProxy(manager_anno));
    	logger.info("isCglibProxy(): " + AopUtils.isCglibProxy(noInterface));
		
	}

	/**
	 * 获取Spring ApplicationContext 几种方式
	 * 
	 * 2015-5-4 下午07:55:37
	 */
	@Test
	public void testApplicationContext() {
		ApplicationContextUtil contextUtil = (ApplicationContextUtil)context.getBean("applicationContextUtil");
		ApplicationContext applicationContext = contextUtil.getApplicationContext();
		
		ApplicationContextUtil2 contextUtil2 = (ApplicationContextUtil2)context.getBean("applicationContextUtil2");
		ApplicationContext applicationContext2 = contextUtil2.getApplicationContext();
		
		Assert.assertTrue(applicationContext == this.context);
		Assert.assertTrue(applicationContext == applicationContext2);
	}
	
	
	@After
    public void tearDown() {
		((ClassPathXmlApplicationContext)context).destroy();
    }
	
	/**
	 * 
	 * 2014-8-7 下午12:23:16
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"apps.xml"});
		// an ApplicationContext is also a BeanFactory (via inheritance)
		//BeanFactory factory = context;
		ProductManager manager = (ProductManager)context.getBean("simpleProductManager");
		
//		manager.daoTest();
		
//		manager.saveBean();
		
//		List<Product> products = manager.getProducts();
//		manager.increasePrice(20);
//		System.out.println(products.size());

	}
	
	private static void another() {
		Resource res = new FileSystemResource("apps.xml");
		BeanFactory factory = new XmlBeanFactory(res);
	}

}
