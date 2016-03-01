package org.demo.context;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * ͨ���̳г����� ApplicationObjectSupport ���� getApplicationContext<br>
 * Spring ��ʼ�� inject ApplicationContext
 * @date 2015-5-4 ����09:57:26
 */
@Component
public class ApplicationContextUtil extends ApplicationObjectSupport {

	public void info() {
		System.out.println("util...");
	}
}
