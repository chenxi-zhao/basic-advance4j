package com.chenxi.javacore.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOClient {
	private static int flag = 1;
	private static int blockSize = 4096;
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	private static ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
	private final static InetSocketAddress severAddress = new InetSocketAddress("127.0.0.1", 7080);

	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		// open the selector
		// connect
		socketChannel.connect(severAddress);

		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);

		Set<SelectionKey> selectionKeys;
		SocketChannel client;
		String receiveText;
		String sendText;
		int count;

		while (true) {
			int nKeys = selector.select(1000);
			if (nKeys > 0) {
				selectionKeys = selector.selectedKeys();
				for (SelectionKey selectionKey : selectionKeys) {
					if (selectionKey.isConnectable()) {
						System.out.println("server connect");
						client = (SocketChannel) selectionKey.channel();
						if (client.isConnectionPending()) {
							client.finishConnect();
							System.out.println("client finished connect opreate ");
							sendBuffer.clear();
							sendBuffer.put("Hello Server".getBytes());
							sendBuffer.flip();
							client.write(sendBuffer);
						}
						client.register(selector, SelectionKey.OP_READ);
					}else if (selectionKey.isReadable()) {
						client = (SocketChannel) selectionKey.channel();
						receiveBuffer.clear();
						count = client.read(receiveBuffer);
						if (count > 0) {
							receiveText = new String(receiveBuffer.array(), 0, count);
							System.out.println("客户端接收到服务端的数据：" + receiveText);
							client.register(selector, SelectionKey.OP_WRITE);
						}
					}else if (selectionKey.isWritable()) {
						sendBuffer.clear();
						client = (SocketChannel) selectionKey.channel();
						sendText = "Msg send to server" + flag++;
						sendBuffer.put(sendText.getBytes());
						sendBuffer.flip();
						client.write(sendBuffer);
						System.out.println("客户端发送数据给服务端：" + sendText);
						client.register(selector, SelectionKey.OP_READ);
					}
				}
				selectionKeys.clear();
			}

		}

	}
}
