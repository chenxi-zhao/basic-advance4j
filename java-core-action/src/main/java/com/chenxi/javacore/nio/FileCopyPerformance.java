package com.chenxi.javacore.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class FileCopyPerformance {
	private final static String from_path = "C:/Users/TracyZro/Desktop/work_res/pythonspider.rar";
	private final static String to_path = "D:/pythonspider.rar";

	private final static Path copy_from = Paths.get(from_path);
	private final static Path copy_to = Paths.get(to_path);

	private static long startTime, elapsedTime;
	// also tested for 16, 32, 64, 128, 256 and 1024
	private static int bufferSizeKB = 4;
	private static int bufferSize = bufferSizeKB * 1024;

	public static void main(String[] args) throws Exception {
		// FileChannel.transferFrom
		transferfrom();
		// FileChannel.transferTo
		transferTo();
		// FileChannel 和非直接模式的 ByteBuffer
		nonDirectBuffer();
		// FileChannel 和直接模式的 ByteBuffer
		directBuffer();
		// FileChannel.map()
		// mapperedBuffer();
		// 使用字节数组和缓冲流
		ioBufferedStream();
		// 使用字节数组和非缓冲流
		ioUnBufferedStream();
		// File.copy()（Path 到 Path）
		copyPath2Path();
		// File.copy()（InputStream 到 Path）
		copyInputStream2Path();
		// File.copy()（Path 到 OutputStream）
		copyPath2OutputStream();

		// randomReadFile();

	}

	// FileChannel.transferFrom
	public static void transferfrom() {

		try {
			RandomAccessFile fromFile = new RandomAccessFile(from_path, "rw");
			FileChannel fileChannel_from = fromFile.getChannel();
			RandomAccessFile toFile = new RandomAccessFile(to_path, "rw");
			FileChannel fileChannel_to = toFile.getChannel();

			startTime = System.nanoTime();
			fileChannel_to.transferFrom(fileChannel_from, 0L, (int) fileChannel_from.size());
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");

			fileChannel_from.close();
			fileChannel_to.close();
			fromFile.close();
			toFile.close();
		} catch (IOException ex) {
			System.err.println(ex);
		}
		deleteCopied(copy_to);
	}

	// FileChannel.transferTo
	public static void transferTo() throws Exception {

		try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
				FileChannel fileChannel_to = (FileChannel.open(copy_to,
						EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {

			startTime = System.nanoTime();
			fileChannel_from.transferTo(0L, fileChannel_from.size(), fileChannel_to);
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		deleteCopied(copy_to);

	}

	// FileChannel 和非直接模式的 ByteBuffer
	public static void nonDirectBuffer() {
		try {
			FileChannel fileChannel_from = FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannel_to = FileChannel.open(copy_to,
					EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));

			startTime = System.nanoTime();
			// 分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢
			ByteBuffer bytebuffer = ByteBuffer.allocate(bufferSize);
			@SuppressWarnings("unused")
			int bytesCount;
			while ((bytesCount = fileChannel_from.read(bytebuffer)) > 0) {
				bytebuffer.flip();
				fileChannel_to.write(bytebuffer);
				bytebuffer.clear();
			}
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");

			fileChannel_from.close();
			fileChannel_to.close();
		} catch (IOException ex) {
			System.err.println(ex);
		}
		deleteCopied(copy_to);
	}

	// FileChannel 和直接模式的 ByteBuffer
	public static void directBuffer() {
		try (FileChannel fileChannel_to = FileChannel.open(copy_to,
				EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
				FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));) {

			startTime = System.nanoTime();
			// 分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对较快。
			ByteBuffer bytebuffer = ByteBuffer.allocateDirect(bufferSize);
			@SuppressWarnings("unused")
			int bytesCount;
			while ((bytesCount = fileChannel_from.read(bytebuffer)) > 0) {
				bytebuffer.flip();
				fileChannel_to.write(bytebuffer);
				bytebuffer.clear();
			}

			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		deleteCopied(copy_to);
	}

	public static void mapperedBuffer() throws Exception {
		try {
			RandomAccessFile fromFile = new RandomAccessFile(from_path, "rw");
			FileChannel fileChannel_from = fromFile.getChannel();
			RandomAccessFile toFile = new RandomAccessFile(to_path, "rw");
			FileChannel fileChannel_to = toFile.getChannel();

			startTime = System.nanoTime();
			int i = 0;
			long size = fileChannel_from.size() / 30;
			ByteBuffer rr, ww = null;
			while ((i < fileChannel_from.size()) && ((fileChannel_from.size() - i)) > size) {
				rr = fileChannel_from.map(MapMode.READ_ONLY, i, size);
				ww = fileChannel_to.map(MapMode.READ_WRITE, i, size);
				ww.put(rr);
				rr.clear();
				ww.clear();
				i += size;
			}

			rr = fileChannel_from.map(MapMode.READ_ONLY, i, fileChannel_from.size() - i);
			ww = fileChannel_to.map(MapMode.READ_WRITE, i, fileChannel_from.size() - i);
			ww.put(rr);
			rr.clear();
			ww.clear();
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");

			fileChannel_from.close();
			fileChannel_to.close();
			fromFile.close();
			toFile.close();
		} catch (IOException ex) {
			System.err.println(ex);
		}

		deleteCopied(copy_to);

	}

	public static void ioBufferedStream() {

		File inFileStr = copy_from.toFile();
		File outFileStr = copy_to.toFile();
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {
			startTime = System.nanoTime();
			byte[] byteArray = new byte[bufferSize];
			int bytesCount;
			while ((bytesCount = in.read(byteArray)) != -1) {
				out.write(byteArray, 0, bytesCount);
			}
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		deleteCopied(copy_to);
	}

	public static void ioUnBufferedStream() {

		File inFileStr = copy_from.toFile();
		File outFileStr = copy_to.toFile();
		try (FileInputStream in = new FileInputStream(inFileStr);
				FileOutputStream out = new FileOutputStream(outFileStr)) {
			startTime = System.nanoTime();
			byte[] byteArray = new byte[bufferSize];
			int bytesCount;
			while ((bytesCount = in.read(byteArray)) != -1) {
				out.write(byteArray, 0, bytesCount);
			}

			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		deleteCopied(copy_to);
	}

	public static void copyPath2Path() {
		try {
			startTime = System.nanoTime();

			Files.copy(copy_from, copy_to, java.nio.file.LinkOption.NOFOLLOW_LINKS);

			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
		} catch (IOException e) {
			System.err.println(e);
		}
		deleteCopied(copy_to);
	}

	public static void copyInputStream2Path() {
		try (InputStream is = new FileInputStream(copy_from.toFile())) {

			startTime = System.nanoTime();
			Files.copy(is, copy_to);
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
		} catch (IOException e) {
			System.err.println(e);
		}
		deleteCopied(copy_to);
	}

	public static void copyPath2OutputStream() {
		try (OutputStream os = new FileOutputStream(copy_to.toFile())) {
			startTime = System.nanoTime();
			Files.copy(copy_from, os);
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	public static void randomReadFile() {

		try (RandomAccessFile read = new RandomAccessFile(
				"C:\\Users\\asus\\Desktop\\cn_windows_7_ultimate_with_sp1_x86_dvd_618763.iso", "r");
				RandomAccessFile writer = new RandomAccessFile(
						"C:\\Users\\asus\\Desktop\\dwTest\\cn_windows_7_ultimate_with_sp1_x86_dvd_618763.iso", "rw");) {
			startTime = System.nanoTime();
			byte[] b = new byte[200 * 1024 * 1024];
			while (read.read(b) != -1) {
				writer.write(b);
			}
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");

		} catch (Exception e) {
			System.err.println(e);
		}
		deleteCopied(copy_to);
	}

	public static void deleteCopied(Path path) {
		try {
			Files.deleteIfExists(path);
		} catch (IOException ex) {
			System.err.println(ex);
		}

	}
}
