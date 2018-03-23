package com.chenxi.javacore.inet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Test_URL {
	public static void main(String[] args) {

		try {
			System.out.println(new Test_URL().getClass().getName());

			// testBasicUrl();
			// testUrlOpenStream();
			testUrlOpenConnection();
			throw new SocketTimeoutException();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testBasicUrl() throws Exception {
		URL imooc = new URL("http://www.immoc.com");
		URL url = new URL(imooc, "/learn/161?username=tt#test");
		System.out.println("协议：" + url.getProtocol());
		System.out.println("端口号：" + url.getPort());
		System.out.println("主机：" + url.getHost());
		System.out.println("文件路径：" + url.getPath());
		System.out.println("文件名：" + url.getFile());
		System.out.println("相对路径：" + url.getRef());
		System.out.println("查询字符串：" + url.getQuery());
	}

	public static void testUrlOpenStream() throws Exception {
		URL url = new URL("http://www.baidu.com");
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String data = br.readLine();
		while (data != null) {
			System.out.println(data);
			data = br.readLine();
		}
		br.close();
		isr.close();
		is.close();
	}

	public static void testUrlOpenConnection() throws Exception {
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection uc = url.openConnection();
			uc.setReadTimeout(5000);
			uc.setConnectTimeout(5000);

			System.out.println(uc.getContentType());
			System.out.println(uc.getContentEncoding());
			System.out.println(new Date(uc.getDate()));

			InputStream is = uc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String data = br.readLine();
			while (data != null) {
				System.out.println(data);
				data = br.readLine();
			}
			br.close();
			isr.close();
			is.close();
		} catch (SocketTimeoutException e) {
			System.out.println("连接超时");
			throw e;
		}
	}
}
