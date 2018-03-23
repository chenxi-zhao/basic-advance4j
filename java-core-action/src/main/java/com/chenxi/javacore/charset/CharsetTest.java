package com.chenxi.javacore.charset;

public class CharsetTest {
	public static void main(String[] args) throws Exception {
		String str = "我就知道A";

		String utf8 = "utf-8";
		printOut(utf8, str.getBytes(utf8));

		String gbk = "gbk";
		printOut(gbk, str.getBytes(gbk));

		String gb2312 = "gb2312";
		printOut(gb2312, str.getBytes(gb2312));
		
		String unicode = "unicode";
		printOut(unicode, str.getBytes(unicode));
		
		String utf16 = "utf-16";
		printOut(utf16, str.getBytes(utf16));
		
		String utf16be = "utf-16be";
		printOut(utf16be, str.getBytes(utf16be));
		
		
		String shz; 
		byte[] hz; 
		
		hz = new byte[4]; 
		hz[0] = (byte) 0xfe; 
		hz[1] = (byte) 0xff; 
		hz[2] = 0x55; 
		hz[3] = 0x4a; 
		shz = new String(hz, "utf-16"); 
		System.out.println(shz); 
		
		hz = new byte[2]; 
		hz[0] = 0x55; 
		hz[1] = 0x4a; 
		shz = new String(hz, "utf-16"); 
		System.out.println(shz); 
		
		hz = new byte[2]; 
		hz[0] = 0x55; 
		hz[1] = 0x4a; 
		shz = new String(hz, "utf-16be"); 
		System.out.println(shz); 
		
		hz = new byte[4]; 
		hz[0] = (byte) 0xff; 
		hz[1] = (byte) 0xfe; 
		hz[2] = 0x4a; 
		hz[3] = 0x55; 
		shz = new String(hz, "utf-16"); 
		System.out.println(shz); 
		
		hz = new byte[2]; 
		hz[0] = 0x4a; 
		hz[1] = 0x55; 
		shz = new String(hz, "utf-16le"); 
		System.out.println(shz); 
		
		System.out.println("啊 UNICODE:U+554A"); 
		System.out.print(Integer.toHexString("啊".charAt(0) >> 8 & 0xff)); 
		System.out.print(" "); 
		System.out.print(Integer.toHexString("啊".charAt(0) & 0xff)); 
		System.out.println(); 
		
	}

	public static void printOut(String charsetName, byte[] bytes) {
		System.out.print("charset:" + charsetName + " --length: " + bytes.length + " --- \t");
		for (byte byte1 : bytes) {
			System.out.print(Integer.toHexString(byte1 & 0xff) + " ");
		}
		System.out.println();
	}
}
