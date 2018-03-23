package com.chenxi.algorithm.sort;

/**
 * 冒泡排序
 * 
 * 遍历若干次数列，每一次比较相邻值看情况交换，最大元素沉底
 * 
 * 稳定算法，时间复杂度O(n^2)
 *
 * 记住最后一次交换发生位置lastExchange的冒泡排序
 * 
 * @author zhaochx1
 *
 */
public class BuddleSort {
	/*
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] array) {
		int i, j;
		int length = array.length;

		for (i = length - 1; i > 0; i--) {
			// 将a[0...i]中最大的数据放在末尾
			for (j = 0; j < i; j++) {

				if (array[j] > array[j + 1]) {
					// 交换a[j]和a[j+1]
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
		}
	}

	/*
	 * 冒泡排序(改进版)
	 */
	public static void bubbleSortBetter(int[] array) {
		int i, j;
		int flag; // 标记
		int length = array.length;
		int k = length - 1,pos = 0;//pos变量用来标记循环里最后一次交换的位置

		for (i = length - 1; i > 0; i--) {

			flag = 0; // 初始化标记为0
			// 将a[0...i]中最大的数据放在末尾
			for (j = 0; j < k; j++) {
				if (array[j] > array[j + 1]) {
					// 交换a[j]和a[j+1]
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;

					// 若发生交换，则设标记为1
					flag = 1;
					pos = j;//循环里最后一次交换的位置 j赋给pos


				}
			}
			k = pos;

			if (flag == 0)
				break; // 若没发生交换，则说明数列已有序。
		}
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		bubbleSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
