package com.chenxi.springiocaop.aop.proxy;

import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {

	private Object target;

	public TransactionHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public void invoke(Object object, Method method) {
		System.out.println("开启事务.....");
		try {
			method.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("提交事务.....");
	}

}
