package com.chenxi.algorithm.sort;

/**
 * 基数排序(Radix Sort)是桶排序的扩展
 * 
 * 它的基本思想是：将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 
 * 具体做法是：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。
 * 
 * 然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 * 
 * @author zhaochx1
 *
 */
public class RadixSort {
	public static final int BUCKET_SIZE = 10;

	/*
	 * 获取数组a中最大值
	 *
	 * 参数说明： a -- 数组 n -- 数组长度
	 */
	private static int getMax(int[] array) {
		int max;

		max = array[0];
		for (int i = 1; i < array.length; i++)
			if (array[i] > max)
				max = array[i];
		return max;
	}

	/**
	 * 对数组按照"某个位数"进行排序(桶排序)
	 *
	 * 例如，对于数组a={50, 3, 542, 745, 2014, 154, 63, 616}； (01)
	 * 
	 * 当exp=1表示按照"个位"对数组a进行排序 (02)
	 * 
	 * 当exp=10表示按照"十位"对数组a进行排序 (03)
	 * 
	 * 当exp=100表示按照"百位"对数组a进行排序 ...
	 * 
	 * @param array
	 *            数组
	 * @param exp
	 *            指数。对数组a按照该指数进行排序。
	 */
	private static void countSort(int[] array, int exp) {
		// int output[a.length]; // 存储"被排序数据"的临时数组
		int[] output = new int[array.length]; // 存储"被排序数据"的临时数组
		int[] buckets = new int[BUCKET_SIZE];

		// 将数据出现的次数存储在buckets[]中
		for (int i = 0; i < array.length; i++)
			buckets[(array[i] / exp) % BUCKET_SIZE]++;

		// 更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的索引。
		for (int i = 1; i < BUCKET_SIZE; i++)
			buckets[i] += buckets[i - 1];

		// 将数据存储到临时数组output[]中
		for (int i = array.length - 1; i >= 0; i--) {
			int bucketIndex = (array[i] / exp) % BUCKET_SIZE;
			output[buckets[bucketIndex] - 1] = array[i];
			buckets[bucketIndex]--;
		}

		// 将排序好的数据赋值给a[]
		for (int i = 0; i < array.length; i++)
			array[i] = output[i];

		output = null;
		buckets = null;
	}

	/**
	 * 基数排序
	 *
	 * @param array
	 *            -- 数组
	 */
	public static void radixSort(int[] array) {
		int exp; // 指数。当对数组按各位进行排序时，exp=1；按十位进行排序时，exp=10；...
		int max = getMax(array); // 数组a中的最大值

		// 从个位开始，对数组a按"指数"进行排序
		for (exp = 1; max / exp > 0; exp *= BUCKET_SIZE)
			countSort(array, exp);
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53, 1005};
		radixSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
