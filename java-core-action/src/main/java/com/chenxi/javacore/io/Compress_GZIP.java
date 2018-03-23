package com.chenxi.javacore.io;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * @since 2015-10-17 13:59:25 GZIP简单使用
 */
public class Compress_GZIP {
	public static void main(String[] args) throws IOException {
		// 用Reader读文件
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("data.gz"), "UTF-8"));
		// 使用缓冲输出流，输出压缩流文件
		BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("data.gz")));
		System.out.println("Writing File 压缩");
		int c;
		while ((c = in.read()) > 0)
			out.write(String.valueOf((char) c).getBytes("UTF-8"));
		in.close();
		out.close();

		System.out.println("Reading File 解压");
		// 用输入解压流读取文件
		BufferedReader in2 = new BufferedReader(
				new InputStreamReader(new GZIPInputStream(new FileInputStream("data.gz")), "UTF-8"));
		String s;
		while ((s = in2.readLine()) != null)
			System.out.println(s);
		in2.close();
	}
}
