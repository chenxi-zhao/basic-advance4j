package com.chenxi.springiocaop.aop.proxy;

public class ProxyClient {
	public static void main(String[] args) throws Exception {
		UserMgr mgr = new UserMgrImpl();

		// 为用户管理添加事务处理
		TransactionHandler transactionHandler = new TransactionHandler(mgr);
		UserMgr userMgr = (UserMgr) Proxy.newProxyInstance(UserMgr.class, transactionHandler);

		// 为用户管理添加显示方法执行时间的功能
		TimeHandler timeHandler = new TimeHandler(userMgr);
		userMgr = (UserMgr) Proxy.newProxyInstance(UserMgr.class, timeHandler);

		userMgr.addUser();
		System.out.println("\r\n==========华丽的分割线==========\r\n");
		userMgr.delUser();
	}
}

interface UserMgr {
	void addUser();

	void delUser();
}

class UserMgrImpl implements UserMgr {

	@Override
	public void addUser() {
		System.out.println("添加用户.....");
	}

	@Override
	public void delUser() {
		System.out.println("删除用户.....");
	}

}
