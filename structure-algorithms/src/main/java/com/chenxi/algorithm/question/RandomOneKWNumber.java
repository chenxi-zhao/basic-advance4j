package com.chenxi.algorithm.question;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by chenxi on 2017/8/8.
 *
 * @author chenxi
 */
public class RandomOneKWNumber {
    public static void main(String[] args) {
//        createRandom();
//        createRandom4();
        String str1 = "aaa";
        String str2 = "bbb";

        String str3 = "aaa" + "bbb";

        String str4 = "aaa" + str2;

        System.out.println(str3 == str4);
        System.out.println(str3 == "aaabbb");

        StringBuffer sb = new StringBuffer("aaa");
        System.out.println(str1 == sb.toString());

        sb.append("bbb");
        System.out.println(str3 == sb.toString());
        System.out.println(str4 == sb.toString());
    }

    private static void createRandom() {

        Random random = new Random();
        long start = System.currentTimeMillis();

        int value = 10;

        ArrayList<Integer> list = new ArrayList<>(value);
        for (int j = 1; j <= value; ++j) {
            list.add(j);
        }

        int index = 0;
        int count = 0;
        int serialRandom = 0;
        int randomRandom = 0;

        while (value > 0) {
            // 生成一个随机的索引值（范围越来越小）
            index = random.nextInt(value);

            // 1. 假设index为20，randomRandom为21，serialRandom为1，交换第0个和第20个数
            // 2. 假设index为50，randomRandom为51，serialRandom为2，交换第1个和第50个数
             System.out.println(index);
            // count + index保证 每一次
            randomRandom = list.get(count + index) == 0 ? count + 1 + index : list.get(count + index);
            // 当前位上的随机数，用于与已生成的做转移
            serialRandom = list.get(count) == 0 ? count + 1 : list.get(count);

            // 把已生成的索引位置的数放到，交换序列数
            list.set(count + index, serialRandom);
            // 依次给每一位设置一个随机数
            list.set(count, randomRandom);
            ++count;
            --value;
        }

        long end = System.currentTimeMillis();

        //----验证是否正确
        /**
         Collections.sort(list);
         int i = 0, size = list.size();
         for (; i < size; ++i) {
         if (list.get(i) != (i + 1))
         System.out.println(i + "error" + list.get(i));
         }*/
        //----验证是否正确

        for (int j = 0; j < list.size(); j++) {
            System.out.println(list.get(j));

        }


        System.out.println("creat3------");
        System.out.println("执行耗时 : " + (end - start) / 1000f + " 秒 ");
        System.out.println("集合大小为" + list.size());
    }

    private static void createRandom4() {

        Random random = new Random();
        long start = System.currentTimeMillis();

        int value = 100000000;

        int[] list = new int[value];

        for (int j = 0; j < value; ++j) {
            list[j] = j + 1;
        }

        int index = 0;
        int count = 0;
        int tmp = 0;
        while (value > 0) {
            index = random.nextInt(value);
//             System.out.println(list[count + index]);
            tmp = list[count + index];
            list[count + index] = list[count];
            list[count] = tmp;
            ++count;
            --value;
        }

        long end = System.currentTimeMillis();

        //----验证是否正确
//        Collections.sort(list);
//        int i = 0, size = list.length;
//        for (; i < size; ++i) {
//            if (list[i] != (i + 1))
//                System.out.println(i + "error" + list[i]);
//        }
        //----验证是否正确

        System.out.println("creat4------");
        System.out.println("执行耗时 : " + (end - start) / 1000f + " 秒 ");
        System.out.println("完了，集合大小为" + list.length);
    }
}
