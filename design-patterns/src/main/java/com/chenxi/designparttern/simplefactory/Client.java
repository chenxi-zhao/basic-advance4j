package com.chenxi.designparttern.simplefactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class Client {
    public static void main(String[] args) {
        Chart chart = ChartFactory.getChart("pie");
        chart.display();
    }
}
