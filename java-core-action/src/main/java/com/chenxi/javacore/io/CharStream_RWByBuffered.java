package com.chenxi.javacore.io;

import java.io.*;

public class CharStream_RWByBuffered {
	public static void main(String[] args) {
		try {
			//File file = new File("peizhi.txt");
			FileInputStream fis = new FileInputStream("peizhi.txt");
			FileOutputStream fos = new FileOutputStream("peizhi_n.txt");
			
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			
			BufferedReader br = new BufferedReader(isr);
			//BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(osw);
			

			String input;
			int count = 0;
			long before = System.currentTimeMillis();
			while((input = br.readLine()) != null){
				count++;
				//bw.write(input);
				pw.println(input);
			}
			
			System.out.println("复制时间：" + (System.currentTimeMillis() - before) + " ms");

			System.out.println("done...");
			System.out.println("读取次数：" + count);
			
			br.close();
			//bw.close();
			pw.close();
			isr.close();
			fis.close();
			osw.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
