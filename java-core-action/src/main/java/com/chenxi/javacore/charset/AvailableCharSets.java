package com.chenxi.javacore.charset;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * 打印可用的编码
 * @author zhaochx1
 *
 */
public class AvailableCharSets {
	public static void main(String[] args) {
		// charset对象的有序映射
		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		// 获取key的迭代器
		Iterator<String> iterator = charSets.keySet().iterator();
		while (iterator.hasNext()) {
			String csName = iterator.next();
			System.out.print(csName);
			// 获取别名的Charset集合的迭代器
			Iterator<String> aliases = charSets.get(csName).aliases().iterator();
			if (aliases.hasNext())
				System.out.print("： ");
			while (aliases.hasNext()) {
				System.out.print(aliases.next());
				if (aliases.hasNext())
					System.out.print(",");
			}
			System.out.println();
		}
	}
}
