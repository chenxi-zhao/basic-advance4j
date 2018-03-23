package com.chenxi.designparttern.abstractfactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class Client {
    public static void main(String[] args) {
        SkinFactory skinFactory = new SpringSkinFactory();
        skinFactory.createButton().display();
        skinFactory.createComboBox().display();
        skinFactory.createTextField().display();
    }
}
