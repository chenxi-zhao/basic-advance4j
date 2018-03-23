package com.chenxi.javacore.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorExample {
	public static void main(String[] args) throws Exception {
		SocketChannel channel = SocketChannel.open();

		Selector selector = Selector.open();

		/*
		 * Channel必须是非阻塞的。所以FileChannel不适用Selector，因为FileChannel不能切换为非阻塞模式。
		 * Socket channel可以正常使用。
		 */
		channel.configureBlocking(false);

		/*
		 * 注意register的第二个参数，这个参数是一个“关注集合”，代表我们关注的channel状态。 有四种基础类型可供监听： Connect
		 * Accept Read Write 一个channel触发了一个事件也可视作该事件处于就绪状态。
		 * 因此当channel与server连接成功后，那么就是“连接就绪”状态。 server
		 * channel接收请求连接时处于“可连接就绪”状态。
		 * channel有数据可读时处于“读就绪”状态。channel可以进行数据写入时处于“写就绪”状态。
		 * 
		 * 上述的四种就绪状态用SelectionKey中的常量表示如下：
		 * 
		 * SelectionKey.OP_CONNECT SelectionKey.OP_ACCEPT SelectionKey.OP_READ
		 * SelectionKey.OP_WRITE 如果对多个事件感兴趣可利用位的或运算结合多个常量，比如：
		 * 
		 * int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
		 */
		channel.register(selector, SelectionKey.OP_READ);

		while (true) {
			// select()方法在返回channel之前处于阻塞状态。
			// select(long timeout)和select做的事一样，不过他的阻塞有一个超时限制。
			// selectNow()不会阻塞，根据当前状态立刻返回合适的channel。
			
			// select()方法的返回值是一个int整形，代表有多少channel处于就绪了。也就是自上一次select后有多少channel进入就绪。
			// 举例来说，假设第一次调用select时正好有一个channel就绪，那么返回值是1
			// 并且对这个channel做任何处理，接着再次调用select，此时恰好又有一个新的channel就绪，
			// 那么返回值还是1，现在我们一共有两个channel处于就绪，但是在每次调用select时只有一个channel是就绪的。

			int readyChannels = selector.select();

			if (readyChannels == 0)
				continue;

			Set<SelectionKey> selectedKeys = selector.selectedKeys();

			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

			while (keyIterator.hasNext()) {

				SelectionKey key = keyIterator.next();

				// int interestSet = selectionKey.interestOps();
				// boolean isInterestedInAccept = interestSet &
				// SelectionKey.OP_ACCEPT;
				// boolean isInterestedInConnect = interestSet &
				// SelectionKey.OP_CONNECT;
				// boolean isInterestedInRead = interestSet &
				// SelectionKey.OP_READ;
				// boolean isInterestedInWrite = interestSet &
				// SelectionKey.OP_WRITE;

				if (key.isAcceptable()) {
					// SelectionKey.channel返回的channel实例需要强转为我们实际使用的具体的channel类型
					ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
					SocketChannel socketChannel = ssChannel.accept();
					socketChannel.configureBlocking(false);
					// a connection was accepted by a ServerSocketChannel.

				} else if (key.isConnectable()) {
					// a connection was established with a remote server.

				} else if (key.isReadable()) {
					// a channel is ready for reading

				} else if (key.isWritable()) {
					// a channel is ready for writing
				}

				keyIterator.remove();
			}
		}
	}
}
