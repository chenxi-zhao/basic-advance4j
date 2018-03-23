package com.chenxi.designparttern.abstractfactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public interface ComboBox {
    void display();
}

//Spring组合框类：具体产品
class SpringComboBox implements ComboBox {
    public void display() {
        System.out.println("显示绿色边框组合框。");
    }
}

//Summer组合框类：具体产品
class SummerComboBox implements ComboBox {
    public void display() {
        System.out.println("显示蓝色边框组合框。");
    }
}