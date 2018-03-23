package com.chenxi.javacore.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferFromToExample {
	public static void main(String[] args) throws Exception {
		transferFrom();
		transferTo();
	}

	public static void transferFrom() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("peizhi.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("peizhi_f.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		// FileChannel.transferFrom方法把数据从通道源传输到FileChannel
		// 把数据从fromChannel传输到toChannel
		// 有些SocketChannel的实现在传输时只会传输哪些处于就绪状态的数据，即使SocketChannel后续会有更多可用数据。
		toChannel.transferFrom(fromChannel, position, count);

		fromChannel.close();
		toChannel.close();
		fromFile.close();
		toFile.close();
	}

	public static void transferTo() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("peizhi.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("peizhi_t.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		// SocketChannel的问题也存在与transferTo.SocketChannel的实现可能只在发送的buffer填充满后才发送
		fromChannel.transferTo(position, count, toChannel);

		fromChannel.close();
		toChannel.close();
		fromFile.close();
		toFile.close();
	}
}
