package com.chenxi.designparttern.factorymethod;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class FileLoggerFactory implements LoggerFactory{
    @Override
    public Logger createLogger() {
        return new FileLogger();
    }
}
