package com.chenxi.designparttern.singleton;

/**
 * Created by chenxi on 2017/7/26.
 * <p>
 * 饿汉式单例
 *
 * @author chenxi
 */
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
