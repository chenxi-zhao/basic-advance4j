package com.chenxi.javacore.io;

import java.io.*;

public class CharStream_CopyFile {
	public static void main(String[] args) {
		try {
			//File file = new File("peizhi.txt");
			FileInputStream fis = new FileInputStream("peizhi.txt");
			FileOutputStream fos = new FileOutputStream("peizhi_n.txt");
			
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			
			char input[] = new char[100];
			int length = 0;
			while((length = isr.read(input)) != -1){
				osw.write(input, 0, length);
			}
			
			isr.close();
			fis.close();
			osw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
