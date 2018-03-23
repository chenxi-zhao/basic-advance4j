package com.chenxi.algorithm.sort;

/**
 * 计数排序
 * 
 * @author zhaochx1
 *
 */
public class CountingSort {
	/*
	 * 桶排序
	 *
	 * 参数说明： a -- 待排序数组 max -- 数组a中最大值的范围
	 */
	public static void countingSort(int[] array, int max) {
		int[] buckets;

		if (array == null || max < 1)
			return;

		// 创建一个容量为max的数组buckets，并且将buckets中的所有数据都初始化为0。
		buckets = new int[max];

		// 1. 计数
		for (int i = 0; i < array.length; i++)
			buckets[array[i]]++;

		// 2. 排序
		for (int i = 0, j = 0; i < max; i++) {
			while ((buckets[i]--) > 0) {
				array[j++] = i;
			}
		}

		buckets = null;
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		countingSort(array, 100);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
