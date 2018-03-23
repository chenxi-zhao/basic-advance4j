package com.chenxi.designparttern.singleton;

/**
 * Created by chenxi on 2017/7/26.
 * <p>
 * Initialization Demand Holder (IoDH)
 *
 * @author chenxi
 */
public class Singleton {
    private Singleton() {
    }

    private static class HolderClass {
        private final static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return HolderClass.instance;
    }

    public static void main(String args[]) {
        Singleton s1, s2;
        s1 = Singleton.getInstance();
        s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
