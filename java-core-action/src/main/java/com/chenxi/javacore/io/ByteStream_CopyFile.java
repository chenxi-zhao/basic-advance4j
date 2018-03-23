package com.chenxi.javacore.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStream_CopyFile {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("C:/Users/TracyZro/Desktop/work_res/pythonspider.rar");
			FileOutputStream fos = new FileOutputStream("C:/Users/TracyZro/Desktop/pythonspider_2.rar");
			
			byte[] input = new byte[1024];
			int count = 0;
			
			long before = System.currentTimeMillis();
			//fis.read(input) === fis.read(input, 0, input.length)
			while (fis.read(input) != -1) {
				count++;
				fos.write(input);
			}
			System.out.println("复制时间：" + (System.currentTimeMillis() - before) + " ms");
			fis.close();
			fos.close();
			System.out.println("done...");
			System.out.println("读取次数：" + count);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
