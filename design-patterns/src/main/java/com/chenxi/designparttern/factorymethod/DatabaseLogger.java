package com.chenxi.designparttern.factorymethod;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class DatabaseLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("数据库日志记录！！");
    }
}
