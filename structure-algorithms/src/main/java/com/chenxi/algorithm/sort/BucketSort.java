package com.chenxi.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 桶排序
 * 
 * 桶排序是稳定的
 * 
 * 桶排序的平均时间复杂度为线性的O(N+C)，其中C=N*(logN-logM)
 * 
 * 如果相对于同样的N，桶数量M越大，其效率越高，最好的时间复杂度达到O(N)。
 * 
 * 当然桶排序的空间复杂度 为O(N+M)，如果输入数据非常庞大，而桶的数量也非常多，则空间代价无疑是昂贵的。
 * 
 * @author zhaochx1
 *
 */
public class BucketSort {
	public static final int BUCKET_SIZE = 10;

	/**
	 * 桶排序算法，对arr进行桶排序，排序结果仍放在arr中
	 * 
	 * @param array
	 */
	public static void bucketSort(int[] array) {
		ArrayList<ArrayList<Integer>> bucket = new ArrayList<>();
		for (int i = 0; i < BUCKET_SIZE; i++) {
			bucket.add(new ArrayList<>());
		}

		// 划分桶并填元素
		for (int i = 0; i < array.length; i++) {
			int k = array[i] / 10;
			bucket.get(k).add(array[i]);
		}
		for (ArrayList<Integer> list : bucket)
			Collections.sort(list);

		// 输出类似鸽巢排序
		int count = 0;
		for (int i = 0; i < BUCKET_SIZE; i++) {
			if (null != bucket.get(i)) {
				for (Integer d : bucket.get(i)) {
					array[count] = d;
					count++;
				}
			}
		}

	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		bucketSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
