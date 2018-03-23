package com.chenxi.springiocaop.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProxyFactoryBean {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"proxy-factory-bean.xml");
		IBussinessService bussinessServiceImpl = applicationContext.getBean("methodProxy", IBussinessService.class);
		bussinessServiceImpl.doBussiness();
		applicationContext.close();
	}
}
