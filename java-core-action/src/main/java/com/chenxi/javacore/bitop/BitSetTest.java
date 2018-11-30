package com.chenxi.javacore.bitop;

import java.util.Arrays;
import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 44, 56, 7, 1024};

        BitSet bitSet = new BitSet();
        for (int anArray : array) {
            bitSet.set(anArray, true);
        }
        System.out.println(bitSet.size());
        System.out.println(bitSet.get(3));

        System.out.println(Arrays.toString(StreamTool.longToByte(1L << 26)));
    }
}
