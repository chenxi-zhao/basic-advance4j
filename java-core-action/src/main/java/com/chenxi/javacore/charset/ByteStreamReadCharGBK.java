package com.chenxi.javacore.charset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteStreamReadCharGBK {
	/*
	 * ==>本java文件采用的编码(GBK)读取GBK编码文本,
	 * 
	 * 实现思路: 1.因为该byte数组只存5个元素,为单.而判断中文包含两个字节,会胜下一个元素.
	 * 2.两个字节判断,如果第一个为中文的一个字节,那么后面的一个也必定为中文的一个字节,两个字节合成一个中文
	 * 3.如果第一个不为中文的一个字节,则第二个可以是,也可能不是,两个都不为中文的字节,则输出两个字符,
	 * 4.如果两个字节前一个不为中文的一个字节后一个是,则把前一个输出,然后continue,
	 * 这时如果这两个字节中的后一个字节是byte数组中的最后一个元素,则补充一个
	 * 5.否则不用补充,只把i多加1(i++)for也有个i++,这样就会多跳过一个元素,从而又是数组中两个新的字节去进行判断,再一次进入while
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// 调用方法
		reader();
		System.out.println((int)(0x7f & 0x80));
	}
	public static void reader() {
		// 封装文件
		File file = new File("C:/Users/TracyZro/Desktop/ceshi.txt");
		try {
			// 创建文件读取流,通过字节流读取
			@SuppressWarnings("resource")
			FileInputStream fs = new FileInputStream(file);

			// 声明长度为5的byte数组
			byte[] b = new byte[5];

			// 声明len变量,用于存储read()方法读取的字节个数,还有其它作用
			int len;
			// 声明两个int变量分别代表byte数组中的两个元素(转换成int型)
			int param1;
			int param2;

			// 使用while循环读取文本文件
			while ((len = fs.read(b)) != -1) {
				// for循环用于循环读取数组中的元素,因为有可以是5有可能最后read()方法读入到byte数组中的不到5个元素,所以用len作为一个判断条件
				for (int i = 0; i < len; i++) {
					// 因为如果存入byte数组中的元素是单数,所以最后会胜下一个元素,它的元素下标为:i=len-1
					if (i == len - 1) {
						// 每一次while循环的得到的byte数组的最后一个元素.
						param1 = b[i];
						// 如果该元素非汉字
						if (param1 >= 0 && param1 <= 127) {
							// 打印该字符
							System.out.print((char) param1);
						} else
						// 否则为中文的一个字节
						{
							// 补充读取一个字节
							len = fs.read();
							if (len == -1) {
								return;
							}
							// 打印这两个字节,也就是中文
							System.out.print(new String(new byte[] { (byte) param1, (byte) len }));
						}
						// byte数组中最后一个元素的两种情况判断完,break;
						break;
					}
					// while循环开始后最先执行的代码
					param1 = b[i];
					param2 = b[i + 1];
					// 如果第一个元素param1不为中文的一个字节,那么就可能为两个非中文的字节,也有可能后一个元素param2为中文的一个字节,

					if ((param1 >= 0 && param1 <= 127)// 如果param1非中文的一个字节
							&& (param2 > 127 || param2 < 0)) // 如果param2是中文的一个字节
					{
						// 打印并跳出当前循环,进行下一次循环
						System.out.print((char) param1);
						continue;
					}
					// 两个字节都为中文或两个都不为中文字节.
					String str = new String(new byte[] { b[i], b[i + 1] });
					System.out.print(str);
					i++;// 打印的是两个字节,i要多加1(用于上面for循环)
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
