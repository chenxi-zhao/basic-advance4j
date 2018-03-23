package com.chenxi.javacore.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
	private int flag = 1;
	private int blockSize = 4096;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
	private Selector selector;

	public NIOServer(int port) throws IOException {
		selector = Selector.open();

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//not blocked
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);
		
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server start -> " + port);

	}

	// monitor
	public void listen() throws IOException {
		while (true) {
			int nKeys = selector.select(1000);
			System.out.println("-----------------nKeys: " + nKeys);
			if (nKeys > 0) {
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					handleKey(selectionKey);
				}
				selector.selectedKeys().clear();  
			}
			
		}
	}

	public void handleKey(SelectionKey selectionKey) throws IOException {
		ServerSocketChannel server = null;
		SocketChannel client = null;

		String recieveText;
		String sendText;
		int count = 0;

		if (selectionKey.isAcceptable()) {
			server = (ServerSocketChannel) selectionKey.channel();
			client = server.accept();
				
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		} else if (selectionKey.isReadable()) {
			client = (SocketChannel) selectionKey.channel();
			count = client.read(receiveBuffer);
			if (count > 0) {
				recieveText = new String(receiveBuffer.array(), 0, count);
				System.out.println("<<<<<<<<<服务端接收到客户端的消息：" + recieveText);
				client.register(selector, SelectionKey.OP_WRITE);
			}
		} else if (selectionKey.isWritable()) {
			sendBuffer.clear();
			client = (SocketChannel) selectionKey.channel();
			// sended datas
			sendText = "msg sent to client:" + flag++;
			sendBuffer.put(sendText.getBytes());
			sendBuffer.flip();
			client.write(sendBuffer);
			System.out.println(">>>>>>>>>>>>>>>>>服务端发送数据给客户端：" + sendText);
			client.register(selector, SelectionKey.OP_READ);
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 7080;

		NIOServer server = new NIOServer(port);
		server.listen();
	}
	
	
}
