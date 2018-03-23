package com.chenxi.javacore.jvm;

public class TestObjAccessible {
	public static TestObjAccessible obj;

	@Override
	public String toString() {
		return "I am CanReliveObj";
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("CanReliveObj finalize called");
		obj = this;
	}

	
	// CanReliveObj finalize called
	// obj is not null
	// second gc
	// obj is null
	// finalize方法只能调用一次
	public static void main(String[] args) throws Exception {
		obj = new TestObjAccessible();
		obj = null;
		System.gc();
		Thread.sleep(1000);
		if (obj == null) {
			System.out.println("obj is null");
		} else
			System.out.println("obj is not null");

		System.out.println("second gc");
		obj = null;
		System.gc();
		Thread.sleep(1000);
		if (obj == null) {
			System.out.println("obj is null");
		} else
			System.out.println("obj is not null");

	}
}
