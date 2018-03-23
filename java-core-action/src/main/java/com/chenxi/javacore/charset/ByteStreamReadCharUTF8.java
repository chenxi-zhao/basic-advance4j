package com.chenxi.javacore.charset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteStreamReadCharUTF8 {
	public static void main(String[] args) {
		// 调用方法
		reader();
		System.out.println((int)(0x7f & 0x80));
	}
	public static void reader() {
		// 封装文件
		File file = new File("peizhi.txt");
		try {
			// 创建文件读取流,通过字节流读取
			FileInputStream fs = new FileInputStream(file);
			int bytes = 0;
			
			while((bytes = fs.read()) != -1){
				//0*******
				if((int)bytes <= 127){
					System.out.print(new String(new byte[] { (byte) bytes}));
				}else if((int)bytes > 192 && (int)bytes <= 223){
					//110*****
					int x = fs.read();
					if(x != -1){
						System.out.print(new String(new byte[] { (byte) bytes, (byte)x}));
					}
				}else if((int)bytes > 224 && (int)bytes <= 239){
					//1110****
					byte[] bb = new byte[3];
					bb[0] = (byte)bytes;
					bb[1] = (byte)fs.read();
					bb[2] = (byte)fs.read();
					
					System.out.print(new String(bb));
				}
				
			}
			
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
