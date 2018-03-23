package com.chenxi.designparttern.abstractfactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public interface Button {
    void display();
}

//Spring按钮类：具体产品
class SpringButton implements Button {
    public void display() {
        System.out.println("显示浅绿色按钮。");
    }
}

//Summer按钮类：具体产品
class SummerButton implements Button {
    public void display() {
        System.out.println("显示浅蓝色按钮。");
    }
}
