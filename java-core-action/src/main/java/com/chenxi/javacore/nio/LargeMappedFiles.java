package com.chenxi.javacore.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件的使用
 */
public class LargeMappedFiles {
    static int length = 0x8FFFFFF; // 128 MB

    public static void main(String[] args) throws IOException {
    	RandomAccessFile randomAccessFile = new RandomAccessFile("c:/data.txt","rw");
    	FileChannel fileChannel = randomAccessFile.getChannel();
    	
    	// 将此通道的文件区域直接映射到内存映射区域中。
        MappedByteBuffer out = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, length);
        // 写入128MB的字符串
        for (int i = 0;i < length; i++)
            out.put((byte)'x');
        System.out.println("Finished writing");
        // 打印其中6个字符
        for (int i = length/2; i < length/2 + 6; i++)
            System.out.print((char)out.get(i));
        
        fileChannel.close();
        randomAccessFile.close();
    }
}
