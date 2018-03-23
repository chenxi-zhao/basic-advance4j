package com.chenxi.algorithm.sort;

/**
 * 选择排序
 * 
 * 它的基本思想是：首先在未排序的数列中找到最小(or最大)元素，然后将其存放到数列的起始位置；
 * 
 * 接着，再从剩余未排序的元素中继续寻找最小(or最大)元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * 优化的选择排序:每次选一个最小值放最前面，选最大一个值放后面
 * 
 * @author zhaochx1
 *
 */
public class SelectSort {

	public static void selectSort(int[] array ) {
		int i; // 有序区的末尾位置
		int j; // 无序区的起始位置
		int min; // 无序区中最小元素位置
		
		int length = array.length;

		for (i = 0; i < length; i++) {
			min = i;

			// 找出"a[i+1] ... a[n]"之间的最小元素，并赋值给min。
			for (j = i + 1; j < length; j++) {
				if (array[j] < array[min])
					min = j;
			}

			// 若min!=i，则交换 a[i] 和 a[min]。
			// 交换之后，保证了a[0] ... a[i] 之间的元素是有序的。
			if (min != i) {
				int tmp = array[i];
				array[i] = array[min];
				array[min] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		int[] array = { 45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53 };
		selectSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
