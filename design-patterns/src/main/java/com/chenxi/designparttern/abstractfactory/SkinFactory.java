package com.chenxi.designparttern.abstractfactory;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public interface SkinFactory {
    Button createButton();

    TextField createTextField();

    ComboBox createComboBox();
}
