package com.chenxi.javacore.io;

import java.io.*;

public class CharStream_ReadWrite {
	public static void main(String[] args) {
		try {
			File file = new File("peizhi.txt");
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			char input[] = new char[100];
			int length = 0;
			
			while((length = isr.read(input)) != -1){
				//System.out.println("length:" + length);
				System.out.print(new String(input, 0, length));
			}
			
			
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
