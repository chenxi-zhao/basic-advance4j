package com.chenxi.javacore.jvm;

import java.util.Vector;

public class TestGCLogs {
	public static void main(String[] args) {
		/**
		 * -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
		 * -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintHeapAtGC
		 */
		Vector<Object> vobj = new Vector<Object>(10);
		for (int i = 0; i < 10000; i++) {
			Object obj = new Object();
			vobj.add(obj);
			// 注意此处Java如何清除内存
			obj = null;
			// GC会清除这一块内存吗？
		}
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.gc();

		for (int i = 0; i < 10000; i++) {
			Object obj = new Object();
			vobj.add(obj);
			// 注意此处Java如何清除内存
			obj = null;
			// GC会清除这一块内存吗？
		}

		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
	}
}
