package com.chenxi.designparttern.singleton;

/**
 * Created by chenxi on 2017/7/26.
 * <p>
 * 懒汉式单例
 *
 * @author chenxi
 */
public class LazySingleton {
    private volatile static LazySingleton instance = null;

    private LazySingleton() {
    }

    /**
     * 延迟加载(Lazy Load)
     * <p>
     * synchronized同步锁
     * <p>
     * 双重检查锁定(Double-Check Locking)
     *
     * @return singleton
     */
    public static LazySingleton getInstance() {
        //第一重判断
        if (instance == null) {
            //锁定代码块
            synchronized (LazySingleton.class) {
                //第二重判断
                if (instance == null) {
                    instance = new LazySingleton(); //创建单例实例
                }
            }
        }
        return instance;
    }
}
