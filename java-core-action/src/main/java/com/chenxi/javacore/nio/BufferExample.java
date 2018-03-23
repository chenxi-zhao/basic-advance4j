package com.chenxi.javacore.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * buffer本质上就是一块内存区，可以用来写入数据，并在稍后读取出来。
 * 这块内存被NIO Buffer包裹起来，对外提供一系列的读写方便开发的接口。
 * 
 * Buffer基本用法（Basic Buffer Usage）
 * 利用Buffer读写数据，通常遵循四个步骤：
 *  - 把数据写入buffer；
 *  - 调用flip；
 *  - 从Buffer中读取数据；
 *  - 调用buffer.clear()或者buffer.compact()
 *  当写入数据到buffer中时，buffer会记录已经写入的数据大小。
 *  当需要读数据时，通过flip()方法把buffer从写模式调整为读模式；在读模式下，可以读取所有已经写入的数据。
 *  
 *  当读取完数据后，需要清空buffer，以满足后续写入操作。 *  清空buffer有两种方式：调用clear()或compact()方法。
 *  clear会清空整个buffer，compact则只清空已读取的数据，
 *  未被读取的数据会被移动到buffer的开始位置，写入位置则近跟着未读数据之后。
 *   
 *   - Capacity Buffer容量大小
 *   - Position 初始化时默认为零，开始写入数据后指向数据之后的一个单元，读取数据时每读取一次数据向后移若干
 *   - Limit 上限，写模式时等于Capacity，读模式时等于能读到的数据上限
 *   Buffer Types
 *   
 * Java NIO有如下具体的Buffer类型：
 *  - ByteBuffer
 *  - MappedByteBuffer
 *  - CharBuffer
 *  - DoubleBuffer
 *  - FloatBuffer
 *  - IntBuffer
 *  - LongBuffer
 *  - ShortBuffer 
 *  
 * @author TracyZro
 *
 */
public class BufferExample {
	public static void main(String[] args) throws Exception {
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();

		File file = new File("peizhi.txt");
		FileInputStream is = new FileInputStream(file);

		FileChannel fileChannel = is.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(20);
		
		//避免中文乱码
		CharBuffer charBuffer = CharBuffer.allocate(20);

		//读取数据到buffer，从channel中读取数据并写入到buffer
		//byteBuffer.put(byte[]);
		while (fileChannel.read(byteBuffer) != -1) {
			byteBuffer.flip();
			decoder.decode(byteBuffer, charBuffer, false);

			charBuffer.flip();
			System.out.print(charBuffer);

			byteBuffer.clear();
			charBuffer.clear();
		}
		// channel.write(buffer) 将数据从buffer中读取写入到channel
		// Buffer.rewind()将position置零
		is.close();
	}
}
