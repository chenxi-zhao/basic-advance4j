package com.chenxi.algorithm.sort;

/**
 * 快速排序
 * <p>
 * 快速排序流程：
 * <p>
 * (1) 从数列中挑出一个基准值。
 * <p>
 * (2) 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)；在这个分区退出之后，该基准就处于数列的中间位置。
 * <p>
 * (3) 递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序。
 * <p>
 * 不稳定算法，时间复杂度O(n^2) （2 4 1 3 1进行排序,第一趟就把后面的1换到前面）
 *
 * @author zhaochx1
 */
public class QuickSort {
    /**
     * 快速排序
     *
     * @param array 待排序的数组
     * @param left  数组的左边界(例如，从起始位置开始排序，则=0)
     * @param right 数组的右边界(例如，排序截至到数组末尾，则array.length-1)
     */
    private static void quickSort(int[] array, int left, int right) {

        if (left < right) {
            int i, j, x;

            i = left;
            j = right;
            x = array[i];
            while (i < j) {
                while (i < j && array[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    array[i++] = array[j];
                while (i < j && array[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    array[j--] = array[i];
            }
            array[i] = x;
            quickSort(array, left, i - 1); /* 递归调用 */
            quickSort(array, i + 1, right); /* 递归调用 */
        }
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {45, 78, 94, 12, 34, 89, 74, 65, 32, 15, 67, 86, 53};
        quickSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

class QuickSortSingle {
    public int[] quickSort(int array[]) {
        sort(array, 0, array.length - 1);
        return array;
    }

    public void sort(int array[], int p, int r) {
        int q = 0;
        if (p < r) {
            q = partition(array, p, r);
            sort(array, p, q - 1);
            sort(array, q + 1, r);
        }
    }

    public int partition(int[] a, int p, int r) {
        int x = a[r];
        int j = p - 1; // j取较大数的下标
        for (int i = p; i <= r - 1; i++) {
            if (a[i] <= x) {
                j++;
                swap(a, j, i);
            }
        }
        swap(a, j + 1, r);
        return j + 1;
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
