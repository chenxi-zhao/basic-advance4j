package com.chenxi.javacore.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Java NIO Channel通道和流非常相似，主要有以下几点区别：
 *  - 通道可以度也可以写，流一般来说是单向的（只能读或者写）。
 *  - 通达可以异步读写。
 *  - 通道总是基于缓冲区Buffer来读写。
 *   正如上面提到的，我们可以从通道中读取数据，写入到buffer；也可以中buffer内读数据，写入到通道中。
 * Channel的实现（Channel Implementations） 
 *  下面列出Java NIO中最重要的集中Channel的实现：
 *  - FileChannel 用于文件的数据读写。 
 *  - DatagramChannel 用于UDP的数据读写。 
 *  - SocketChannel 用于TCP的数据读写。
 *  - ServerSocketChannel 允许我们监听TCP链接请求，每个请求会创建会一个SocketChannel
 * 
 * @author TracyZro
 */
public class ChannelExample {
	public static void main(String[] args) throws Exception {
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		
		File file = new File("peizhi.txt");
		FileInputStream is = new FileInputStream(file);
		
	    FileChannel fileChannel = is.getChannel();
	    
	    // 文件大小
	    fileChannel.size();
	    // 截取指定大小文件
	    // fileChannel.truncate(1024);
	    // 把未写入磁盘的数据强制写入磁盘
	    // fileChannel.force(true);
	    
	    

	    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
	    CharBuffer charBuffer = CharBuffer.allocate(20);  
	    
	    while (fileChannel.read(byteBuffer) != -1) {
	      byteBuffer.flip();
	      decoder.decode(byteBuffer, charBuffer, false);
	      
	      charBuffer.flip();  
	      System.out.print(charBuffer);

	      byteBuffer.clear();
	      charBuffer.clear();
	    }
	    is.close();
	}
}
