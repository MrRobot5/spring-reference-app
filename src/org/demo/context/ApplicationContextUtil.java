package org.demo.context;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * 通过继承抽象类 ApplicationObjectSupport 调用 getApplicationContext<br>
 * Spring 初始化 inject ApplicationContext
 * @date 2015-5-4 下午09:57:26
 */
@Component
public class ApplicationContextUtil extends ApplicationObjectSupport {

	public void info() {
		System.out.println("util...");
	}
}
