package com.chenxi.designparttern.simplefactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class LineChart implements Chart{
    public LineChart() {
        System.out.println("创建折线图！");
    }

    public void display() {
        System.out.println("显示折线图！");
    }
}
