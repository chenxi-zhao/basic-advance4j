package com.chenxi.algorithm.question;

/**
 * Created by chenxi on 2017/8/8.
 *
 * @author chenxi
 */
public class SquareRoot {
    public static double squareRoot(double n) {
        double x = 1;
        double temp = 1;
        do {
            temp = x;                //保存上一次计算的值
            x = 0.5 * (x + n / x);    //这个就是牛顿迭代法的基本公式
        } while (Math.abs(x - temp) > 1E-15);        //如果两次求值差的绝对值小于0.00001则结束循环
        return x;
    }

    static double sqrt(double C) {
        if (C < 0)
            return Double.NaN;
        double err = 1E-15;
        double cur = C;
        while (Math.abs(C - cur * cur) > err)
            cur = (cur + C / cur) / 2;
        return cur;
    }

    static double root(double C, int k) {
        if (C < 0)
            return Double.NaN;
        double err = 1E-15;
        double cur = C;

        int count = 0;
        while (Math.abs(C - Math.pow(cur, k)) > err) {
            cur = ((k - 1) * cur) / k + C / (k * Math.pow(cur, k - 1));
            count++;
        }
        System.out.println("迭代次数" + count);
        return cur;
    }


    public static void main(String[] args) {
        System.out.println(sqrt(36));
        System.out.println(squareRoot(16));
        System.out.println(root(8, 3));
    }
}
