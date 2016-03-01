package org.demo.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 通过实现接口 ApplicationContextAware 保存ApplicationContext<br>
 * Spring 初始化 inject ApplicationContext
 * @date 2015-5-4 下午09:57:50
 */
@Component
public class ApplicationContextUtil2 implements ApplicationContextAware {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private ApplicationContext applicationContext;
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationContext = applicationContext;
		logger.debug("ApplicationContextAware 注入属性 applicationContext...");
	}

}
