package com.chenxi.javacore.proxy;

public class AccountImpl implements IAccount {

	@Override
	public void queryAccount() {
		System.out.println("查询方法...");
	}

	@Override
	public void updateAccount() {
		System.out.println("修改方法...");
	}

}
