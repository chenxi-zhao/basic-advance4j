package com.chenxi.algorithm.question;

/**
 * Created by chenxi on 2017/8/9.
 * <p>
 * 优化、缓存已生成的前序列
 *
 * @author chenxi
 */
public class Fibonacci {
    public static void main(String[] args) {
        fibonacci1();
    }

    public static void fibonacci1() {
        int a = 1, b = 1, c = 0;
        System.out.println("斐波那契数列前20项为：");
        System.out.print(a + "\t" + b + "\t");
        for (int i = 1; i <= 18; i++) {
            c = a + b;
            a = b;
            b = c;
            System.out.print(c + "\t");
            if ((i + 2) % 5 == 0)
                System.out.println();
        }
    }

    public static void fibonacci2() {
        int arr[] = new int[20];
        arr[0] = arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println("斐波那契数列的前20项如下所示：");
        for (int i = 0; i < arr.length; i++) {
            if (i % 5 == 0)
                System.out.println();
            System.out.print(arr[i] + "\t");
        }
    }


    public static int fibonacci3(int i) {
        if (i == 1 || i == 2)
            return 1;
        else
            return fibonacci3(i - 1) + fibonacci3(i - 2);
    }
}
