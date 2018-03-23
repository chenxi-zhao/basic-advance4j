package com.chenxi.designparttern.simplefactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class PieChart implements Chart{
    public PieChart() {
        System.out.println("创建饼状图！");
    }

    public void display() {
        System.out.println("显示饼状图！");
    }
}
