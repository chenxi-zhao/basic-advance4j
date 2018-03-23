package com.chenxi.designparttern.simplefactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class HistogramChart implements Chart{
    public HistogramChart() {
        System.out.println("创建柱状图！");
    }

    public void display() {
        System.out.println("显示柱状图！");
    }
}
