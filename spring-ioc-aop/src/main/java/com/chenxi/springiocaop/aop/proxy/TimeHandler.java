package com.chenxi.springiocaop.aop.proxy;

import java.lang.reflect.Method;
import java.util.Date;

public class TimeHandler implements InvocationHandler {
	private Object target;

	public TimeHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public void invoke(Object object, Method method) {
		System.out.println("开始时间: " + new Date().toString());
		try {
			method.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结束时间: " + new Date().toString());
	}

}
