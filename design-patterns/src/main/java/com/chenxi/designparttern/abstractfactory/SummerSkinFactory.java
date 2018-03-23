package com.chenxi.designparttern.abstractfactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class SummerSkinFactory implements SkinFactory {
    @Override
    public Button createButton() {
        return new SummerButton();
    }

    @Override
    public TextField createTextField() {
        return new SummerTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
