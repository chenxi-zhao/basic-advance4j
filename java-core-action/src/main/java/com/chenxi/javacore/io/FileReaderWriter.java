package com.chenxi.javacore.io;

import java.io.*;

public class FileReaderWriter {
	public static void main(String[] args) {
		try {
			//FileReader继承自InputStreamReader使用方法基本一样
			FileReader fr = new FileReader("peizhi.txt");
			FileWriter fw = new FileWriter("peizhi_n.txt");
			
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			
			br.close();
			bw.close();
			fr.close();
			fw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
