package com.chenxi.designparttern.abstractfactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class SpringSkinFactory implements SkinFactory {
    @Override
    public Button createButton() {
        return new SpringButton();
    }

    @Override
    public TextField createTextField() {
        return new SpringTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SpringComboBox();
    }
}
