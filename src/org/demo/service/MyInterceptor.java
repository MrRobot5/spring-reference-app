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
 * ����������
 * �������ã�1)��Ϊbean,ע�ᵽspring container
 * 		2) ��Ϊ����aspect, ����������
 * 
 * @author yangp
 * 2014-8-7����10:41:27
 */
//@Component
@Aspect
public class MyInterceptor {

	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
    * @Pointcut("execution(* com.hf.service..*.*(..))")���ʽ����
    * ��һ��* ��ʾ����ֵ����Ϊ��������
    * com.hf.service.. �������ʾ��·���µ��Ӱ�����ҲҪ����
    * com.hf.service..*.* �Ӱ����������е����з��� ��һ��*���� �ڶ���*�Ƿ���
    * (..)�������������� ���п��޿ɶ����
    * **/
	
	// ���������
	@Pointcut("execution (* org.demo.service..*.*(..))")
	@SuppressWarnings({"unused"})
	private void andMethod() {
		// just template
	}

	// ������ ֻ���ط��ϲ������͵ķ���
	@Before("andMethod() && args(name)")
	public void doAccessCheck(int name) {
		logger.info("@Before֪ͨ	AccessCheck");
		logger.info("���ع���1) �������ú��в�����2)���Ҳ�������Ϊint");
	}

	// ������ֵ���޷���ֵ�ķ���resultΪnull
	// ���ط���ִ�к��ȡ����ֵ����
	@AfterReturning(pointcut = "andMethod()", returning = "result")
	public void doFilterReturning(String result) {
		logger.info("@After֪ͨ	FilterReturning : " + result);
	}

	@After("andMethod()")
	public void doAfter() {
		logger.info("@After֪ͨ");
	}

	@AfterThrowing(pointcut = "andMethod()", throwing = "e")
	public void doAfterThrowing(Exception e) {
		logger.info("@AfterThrowing֪ͨ�� �����쳣");
	}

	@Around("andMethod()")
	// ����֪ͨ
	public Object doBasecProfiling(ProceedingJoinPoint pjp) throws Throwable {
		//�ж��Ƿ���Ȩ��
		logger.info("@Around����֪ͨ");
		Object result = pjp.proceed();
		logger.info("@Around�뿪֪ͨ, ���ִ��");
		
		return result;
	}
	
}
