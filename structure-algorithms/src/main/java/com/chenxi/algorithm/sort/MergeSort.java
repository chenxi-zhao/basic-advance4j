package com.chenxi.algorithm.sort;

/**
 * 归并排序
 * 
 * 稳定算法，时间复杂度O(N*lgN)
 * 
 * @author zhaochx1
 *
 */
public class MergeSort {
	public static void mergeSort(int array[]) {
		mergeSort(array, 0, array.length - 1);
	}

	private static void mergeSort(int[] array, int start, int end) {
		int mid;
		if (start < end) {
			mid = (start + end) >> 1;
			mergeSort(array, start, mid);
			mergeSort(array, mid + 1, end);

			// array[start...mid] 和 array[mid...end]是两个有序空间，
			// 将它们排序成一个有序空间array[start...end]
			merge(array, start, mid, end);
		}
	}

	/**
	 * 将一个数组中的两个相邻有序区间合并成一个
	 * 
	 * @param array
	 *            包含两个有序区间的数组
	 * @param start
	 *            第1个有序区间的起始地址。
	 * @param mid
	 *            第1个有序区间的结束地址。也是第2个有序区间的起始地址
	 * @param end
	 *            第2个有序区间的结束地址
	 */
	private static void merge(int[] array, int start, int mid, int end) {
		int leftLength = mid - start + 1;
		int rightLength = end - mid;
		int[] left = new int[leftLength + 1];
		int[] right = new int[rightLength + 1];

		// 取出需要合并的两个子数组
		int i, j;
		for (i = 0; i < leftLength; i++)
			left[i] = array[start + i];
		for (j = 0; j < rightLength; j++)
			right[j] = array[mid + j + 1];

		left[i] = Integer.MAX_VALUE;
		right[j] = Integer.MAX_VALUE;
		i = j = 0;
		for (int k = start; k <= end; k++) {
			if (left[i] <= right[j])
				array[k] = left[i++];
			else
				array[k] = right[j++];
		}
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		mergeSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
