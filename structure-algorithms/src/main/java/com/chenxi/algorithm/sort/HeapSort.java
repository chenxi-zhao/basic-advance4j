package com.chenxi.algorithm.sort;

/**
 * 堆排序（基于优先级队列，Priority Queue）
 * 
 * 不稳定算法，时间复杂度O(N*lgN)。
 * 
 * @author zhaochx1
 *
 */
public class HeapSort {
	/**
	 * (最大)堆的向下调整算法
	 *
	 * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
	 * 其中，N为数组下标索引值，如数组中第1个数对应的N为0。
	 * 
	 * @param array
	 * @param start
	 *            被下调节点的起始位置(一般为0，表示从第1个开始)
	 * @param end
	 *            截至范围(一般为数组中最后一个元素的索引)
	 */
	public static void maxHeapDown(int[] array, int start, int end) {
		int current = start; // 当前(current)节点的位置
		int l = 2 * current + 1; // 左(left)孩子的位置
		int tmp = array[current]; // 当前(current)节点的大小

		for (; l <= end; current = l, l = 2 * l + 1) {
			// "l"是左孩子，"l+1"是右孩子
			if (l < end && array[l] < array[l + 1])
				l++; // 左右两孩子中选择较大者，即m_heap[l+1]
			if (tmp >= array[l])
				break; // 调整结束
			else { // 交换值
				array[current] = array[l];
				array[l] = tmp;
			}
		}
	}

	/*
	 * 堆排序(从小到大)
	 *
	 * http://images.cnitblog.com/blog/94031/201403/022349560186587.png
	 *
	 * 参数说明： array--待排序的数组
	 */
	public static void heapSortAsc(int[] array) {
		int i, tmp;
		int length = array.length;
		// 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
		for (i = length / 2 - 1; i >= 0; i--)
			maxHeapDown(array, i, length - 1);

		// 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
		for (i = length - 1; i > 0; i--) {
			// 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
			tmp = array[0];
			array[0] = array[i];
			array[i] = tmp;
			// 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
			// 即，保证a[i-1]是a[0...i-1]中的最大值。
			maxHeapDown(array, 0, i - 1);
		}
	}

	/**
	 * (最小)堆的向下调整算法
	 *
	 * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
	 * 其中，N为数组下标索引值，如数组中第1个数对应的N为0。
	 * 
	 * @param array
	 * @param start
	 *            被下调节点的起始位置(一般为0，表示从第1个开始)
	 * @param end
	 *            截至范围(一般为数组中最后一个元素的索引)
	 */
	public static void minHeapDown(int[] array, int start, int end) {
		int current = start; // 当前(current)节点的位置
		int l = 2 * current + 1; // 左(left)孩子的位置
		int tmp = array[current]; // 当前(current)节点的大小

		for (; l <= end; current = l, l = 2 * l + 1) {
			// "l"是左孩子，"l+1"是右孩子
			if (l < end && array[l] > array[l + 1])
				l++; // 左右两孩子中选择较小者
			if (tmp <= array[l])
				break; // 调整结束
			else { // 交换值
				array[current] = array[l];
				array[l] = tmp;
			}
		}
	}

	/*
	 * 堆排序(从大到小)
	 *
	 * 参数说明： a -- 待排序的数组 n -- 数组的长度
	 */
	public static void heapSortDesc(int[] array) {
		int i, tmp;
		int length = array.length;

		// 从(n/2-1) --> 0逐次遍历每。遍历之后，得到的数组实际上是一个最小堆。
		for (i = length / 2 - 1; i >= 0; i--)
			minHeapDown(array, i, length - 1);

		// 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
		for (i = length - 1; i > 0; i--) {
			// 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最小的。
			tmp = array[0];
			array[0] = array[i];
			array[i] = tmp;
			// 调整a[0...i-1]，使得a[0...i-1]仍然是一个最小堆。
			// 即，保证a[i-1]是a[0...i-1]中的最小值。
			minHeapDown(array, 0, i - 1);
		}
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		// heapSortDesc(array);
		heapSortAsc(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
