package com.chenxi.designparttern.adapter;

/**
 * Created by chenxi on 2017/7/31.
 *
 * @author chenxi
 */
public interface ScoreOperation {
    int[] sort(int array[]); //成绩排序

    int search(int array[], int key); //成绩查找
}
