package com.chenxi.javacore.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterGatherExample {
	public static void main(String[] args) throws Exception {
		File file = new File("peizhi.txt");
		FileInputStream is = new FileInputStream(file);

		FileChannel fileChannel = is.getChannel();
		/*
		 * Scattering Reads "scattering read"是把数据从单个Channel写入到多个buffer
		 * read()方法内部会负责把数据按顺序写进传入的buffer数组内。 一个buffer写满后，接着写到下一个buffer中。
		 */
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body = ByteBuffer.allocate(1024);
		ByteBuffer[] buffetArray = { header, body };
		fileChannel.read(buffetArray);
		header.flip();
		body.flip();

		System.out.println("header:" + new String(header.array()));
		System.out.println("body:" + new String(body.array()));

		/*
		 * "gathering write"把多个buffer的数据写入到同一个channel中，下面是示意图： ByteBuffer header
		 * = ByteBuffer.allocate(128); ByteBuffer body =
		 * ByteBuffer.allocate(1024); //write data into buffers ByteBuffer[]
		 * bufferArray = { header, body }; channel.write(bufferArray);
		 * 
		 * 内部机会按顺序将数组内的内容写进channel，这里需要注意，写入的时候针对的是buffer中position到limit之间的数据。
		 * 也就是如果buffer的容量是128字节，但它只包含了58字节数据，那么写入的时候只有58字节会真正写入。因此gathering
		 * write是可以适用于可变大小的message的，这和scattering reads不同。
		 */

		is.close();
	}
}
