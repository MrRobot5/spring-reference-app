package org.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定义切面类
 * 必须配置：1)作为bean,注册到spring container
 * 		2) 作为切面aspect, 完整的配置
 * 
 * @author yangp
 * 2014-8-7下午10:41:27
 */
//@Component
@Aspect
public class MyInterceptor {

	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
    * @Pointcut("execution(* com.hf.service..*.*(..))")表达式含义
    * 第一个* 表示返回值类型为任意类型
    * com.hf.service.. 两个点表示包路径下的子包的类也要拦截
    * com.hf.service..*.* 子包的所有类中的所有方法 第一个*是类 第二个*是方法
    * (..)代表方法参数随意 可有可无可多可少
    * **/
	
	// 定义切入点
	@Pointcut("execution (* org.demo.service..*.*(..))")
	@SuppressWarnings({"unused"})
	private void andMethod() {
		// just template
	}

	// 带参数 只拦截符合参数类型的方法
	@Before("andMethod() && args(name)")
	public void doAccessCheck(int name) {
		logger.info("@Before通知	AccessCheck");
		logger.info("拦截规则：1) 方法调用含有参数，2)并且参数类型为int");
	}

	// 带返回值的无返回值的方法result为null
	// 拦截方法执行后获取返回值对象
	@AfterReturning(pointcut = "andMethod()", returning = "result")
	public void doFilterReturning(String result) {
		logger.info("@After通知	FilterReturning : " + result);
	}

	@After("andMethod()")
	public void doAfter() {
		logger.info("@After通知");
	}

	@AfterThrowing(pointcut = "andMethod()", throwing = "e")
	public void doAfterThrowing(Exception e) {
		logger.info("@AfterThrowing通知： 发生异常");
	}

	@Around("andMethod()")
	// 环绕通知
	public Object doBasecProfiling(ProceedingJoinPoint pjp) throws Throwable {
		//判断是否与权限
		logger.info("@Around进入通知");
		Object result = pjp.proceed();
		logger.info("@Around离开通知, 最后执行");
		
		return result;
	}
	
}
