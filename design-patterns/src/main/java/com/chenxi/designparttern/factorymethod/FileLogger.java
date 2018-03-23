package com.chenxi.designparttern.factorymethod;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class FileLogger implements Logger{
    @Override
    public void writeLog() {
        System.out.println("文件日志记录！！！");
    }
}
