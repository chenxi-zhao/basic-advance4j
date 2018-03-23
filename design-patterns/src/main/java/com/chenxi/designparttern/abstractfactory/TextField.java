package com.chenxi.designparttern.abstractfactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public interface TextField {
    void display();
}

//Spring文本框类：具体产品
class SpringTextField implements TextField {
    public void display() {
        System.out.println("显示绿色边框文本框。");
    }
}

//Summer文本框类：具体产品
class SummerTextField implements TextField {
    public void display() {
        System.out.println("显示蓝色边框文本框。");
    }
}