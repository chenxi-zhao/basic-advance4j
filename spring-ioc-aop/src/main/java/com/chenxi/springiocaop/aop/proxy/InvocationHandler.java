package com.chenxi.springiocaop.aop.proxy;

import java.lang.reflect.Method;

public interface InvocationHandler {
	public void invoke(Object object, Method method);
}
