package com.chenxi.javacore.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class JdkProxyFactory implements InvocationHandler {
	private Object delegate;

	public Object bind(Object delegate) {
		this.delegate = delegate;
		return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start time is: " + new Date());
		Object result = null;
		try {
			result = method.invoke(delegate, args);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("end time is: " + new Date());
		return result;
	}
	
	
	public static void main(String[] args) {
		IAccount account = (IAccount)(new JdkProxyFactory().bind(new AccountImpl()));
		account.queryAccount();
		account.updateAccount();
	}

}
