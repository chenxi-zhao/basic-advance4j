package com.chenxi.javacore.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Test_InetAddress {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println("计算机名：" + address.getHostName());
		System.out.println("计算机IP：" + address.getHostAddress());
		System.out.println("计算机CanonicalName：" + address.getCanonicalHostName());
		byte[] bytes = address.getAddress();
		System.out.println("字节数组形式的IP：" + Arrays.toString(bytes));
		System.out.println("计算机名/IP：" + address);
		
		System.out.println("------------------------------");
		
		
		//InetAddress zhaochx = InetAddress.getByName("zhaochx");
		InetAddress zhaochx = InetAddress.getByName("10.1.42.104");
		System.out.println("计算机名：" + zhaochx.getHostName());
		System.out.println("计算机IP：" + zhaochx.getHostAddress());
	}
}
