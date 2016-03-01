package org.demo.service;

import org.springframework.stereotype.Component;

/**
 * 9.1.3 AOP Proxies
 * 
 * Spring AOP defaults to using standard J2SE dynamic proxies for AOP proxies. 
 * This enables any interface (or set of interfaces) to be proxied.
 * 
 * CGLIB is used by default if a business object does not implement an interface.
 * 
 * @author yangp
 * 2014年9月22日 下午10:18:46
 */
@Component
public class NoInterface {

	public void say() {
		System.out.println("no interface!");
	}
	
	public void say(String name) {
		System.out.println("say(String name)!");
	}
	
}
