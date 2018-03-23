package com.chenxi.algorithm.sort;

/**
 * 希尔排序
 * 
 * 对于n个待排序的数列，取一个小于n的整数gap(gap被称为步长)将待排序元素分成若干个组子序列，所有距离为gap的倍数的记录放在同一个组中；
 * 
 * 然后，对各组内的元素进行直接插入排序。
 * 
 * 这一趟排序完成之后，每一个组的元素都是有序的。
 * 
 * 然后减小gap的值，并重复执行上述的分组和排序。重复这样的操作，当gap=1时，整个数列就是有序的。
 * 
 * 不稳定算法.
 * 
 * 希尔排序的时间复杂度与增量(即，步长gap)的选取有关。例如，当增量为1时，希尔排序退化成了直接插入排序，此时的时间复杂度为O(N²)，
 * 而Hibbard增量的希尔排序的时间复杂度为O(N3/2)。
 * 
 * @author zhaochx1
 *
 */
public class ShellSort {
	/**
	 * 希尔排序
	 *
	 */
	public static void shellSort(int[] array) {
		int length = array.length;

		// gap为步长，每次减为原来的一半。
		for (int gap = length / 2; gap > 0; gap /= 2) {

			// 共gap个组，对每一组都执行直接插入排序
			for (int i = 0; i < gap; i++) {
				groupSort(array, length, i, gap);
			}
		}
	}

	/**
	 * 对希尔排序中的单个组进行排序
	 *
	 * 参数说明： a -- 待排序的数组 n -- 数组总的长度 i -- 组的起始位置 gap -- 组的步长
	 *
	 * 组是"从i开始，将相隔gap长度的数都取出"所组成的！
	 */
	public static void groupSort(int[] array, int length, int i, int gap) {

		for (int j = i + gap; j < length; j += gap) {

			// 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
			if (array[j] < array[j - gap]) {

				int tmp = array[j];
				int k = j - gap;
				while (k >= 0 && array[k] > tmp) {
					array[k + gap] = array[k];
					k -= gap;
				}
				array[k + gap] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		shellSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
