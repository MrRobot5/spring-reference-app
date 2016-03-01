package org.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundExample {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Around("execution (* org.demo.service..*.*(..))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		
		Object[] objects = pjp.getArgs();
		logger.info("start @Around :" + objects[0]);
		// start stopwatch
		Object retVal = pjp.proceed();
		// stop stopwatch
		logger.info("stop stopwatch");
		return retVal;
	}
}
