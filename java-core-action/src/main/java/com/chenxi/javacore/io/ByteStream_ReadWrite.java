package com.chenxi.javacore.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ByteStream_ReadWrite {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("peizhi.txt");
			byte[] input = new byte[22];
			int length = 0;
			//fis.read(input);
			while((length = fis.read(input)) != -1){
				System.out.print(new String(input, 0, length));
			}
			
			
			FileOutputStream fos = new FileOutputStream("test_w.txt");
			String text = "write 12345 写出数据"; 
			byte[] output = text.getBytes("utf-8");
			fos.write(output);
			
			
			
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
