package com.chenxi.javacore.jvm;

public class JVMParam {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		byte[] b = new byte[10*1024*1024];
		
		System.gc();
		
		System.out.println("Xmx=" + Runtime.getRuntime().maxMemory()/1024.0/1024 + "M");
		
		System.out.println("free mem=" + Runtime.getRuntime().freeMemory()/1024.0/1024 + "M");
		
		System.out.println("total=" + Runtime.getRuntime().totalMemory()/1024.0/1024 + "M");
		

	}
}
