package com.chenxi.javacore.proxy;

public class StaticProxy {
	public static void main(String[] args) {
		System.out.println(StaticProxy.class.getPackage().getName());
		System.out.println(StaticProxy.class.getResource("").getPath());
		System.out.println(System.getProperty("user.dir"));
		System.out.println(StaticProxy.class.getSimpleName());

		AccountImpl accountImpl = new AccountImpl();
		// 在这里传入要调用的业务对象
		AccountProxy accountProxy = new AccountProxy(accountImpl);
		// 开始调用业务对象的方法，这两个方法都被增强了。
		accountProxy.updateAccount();
		accountProxy.queryAccount();
	}
}

class AccountProxy implements IAccount {
	private AccountImpl accountImpl;

	/**
	 * 重写默认构造函数
	 * 
	 * @param accountImpl
	 *            :真正要执行业务的对象
	 */
	public AccountProxy(AccountImpl accountImpl) {
		this.accountImpl = accountImpl;
	}

	@Override
	public void queryAccount() {
		System.out.println("业务处理之前...");
		// 调用委托类的方法，这是具体的业务方法
		accountImpl.queryAccount();
		System.out.println("业务处理之后...");
	}

	@Override
	public void updateAccount() {
		System.out.println("业务处理之前...");
		// 调用委托类的方法;
		accountImpl.updateAccount();
		System.out.println("业务处理之后...");
	}
}