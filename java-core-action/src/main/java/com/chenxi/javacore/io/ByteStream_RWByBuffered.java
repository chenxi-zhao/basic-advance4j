package com.chenxi.javacore.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ByteStream_RWByBuffered {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("C:/Users/TracyZro/Desktop/work_res/pythonspider.rar");
			BufferedInputStream bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream("C:/Users/TracyZro/Desktop/pythonspider_1.rar");
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			byte[] input = new byte[1024 * 1024];
			int count = 0;

			long before = System.currentTimeMillis();
			while (bis.read(input) != -1) {
				count++;
				bos.write(input);
			}
			System.out.println("复制时间：" + (System.currentTimeMillis() - before) + " ms");

			bis.close();
			fis.close();
			bos.close();
			fos.close();

			System.out.println("done...");
			System.out.println("读取次数：" + count);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
