package org.demo.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * ����@Transactional ̽�ֻ���
 * 
 * http://blog.csdn.net/kook_okko/article/details/4565805
 * @author yangp
 * 2014-8-7����11:31:18
 */
@Component
@Aspect
public class SimpleProfiler {
    
    // ���������
	@Pointcut("execution (* org.demo.service..*.*(..))")
	private void tempMethod() {
		// just template
	}

    // this method is the around advice
	@Around("tempMethod()")
    public Object profile(ProceedingJoinPoint call) throws Throwable {
        Object returnValue;
        StopWatch clock = new StopWatch(getClass().getName());
        try {
            clock.start(call.toShortString());
            returnValue = call.proceed();
        } finally {
            clock.stop();
            System.out.println(clock.prettyPrint());
        }
        return returnValue;
    }

}
