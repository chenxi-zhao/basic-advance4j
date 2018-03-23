package com.chenxi.algorithm.sort;

/**
 * 插入排序
 * 
 * 把n个待排序的元素看成为一个有序表和一个无序表。
 * 
 * 开始时有序表中只包含1个元素， 无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，
 * 
 * 将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。
 * 
 * 稳定算法，时间复杂度O(n^2)
 * 
 * @author zhaochx1
 *
 */
public class InsertSort {
	public static void insertSort(int array[]) {
		int key, j;

		for (int i = 1; i < array.length; i++) {
			key = array[i];
			j = i - 1;

			// 移动已排序的元素寻找合适的插入位置
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}
			// 插入元素
			array[j + 1] = key;
		}
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		insertSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
