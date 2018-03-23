package com.chenxi.designparttern.factorymethod;

/**
 * Created by chenxi on 2017/7/25.
 *
 * @author chenxi
 */
public class Client {
    public static void main(String[] args) {
        LoggerFactory factory = new FileLoggerFactory();
        Logger logger = factory.createLogger();
        logger.writeLog();
    }
}
