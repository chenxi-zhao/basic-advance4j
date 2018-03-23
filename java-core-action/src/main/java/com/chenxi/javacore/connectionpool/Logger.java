package com.chenxi.javacore.connectionpool;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by chenxi on 2017/8/10.
 * <p>
 * 日志文件创建维护类，单例模式
 *
 * @author chenxi
 */
public class Logger {

    private static Logger logger = null;
    private PrintWriter log = null;
    private static int level = 0;
    private Class<?> c = null;
    private static final int DEBUGLEVEL = 1;
    private static final int INFOLEVEL = 2;
    private static final int ERRORLEVEL = 3;

    private Logger(Class<?> c) {
        String logFileName = PropertiesManager.getProperty("logfile", "DBlog.txt");
        String str_level = PropertiesManager.getProperty("loglevel", "3");
        this.c = c;
        level = Integer.parseInt(str_level);
        try {
            log = new PrintWriter(new FileWriter(logFileName), true);
        } catch (IOException e) {
            System.err.println("无法打开日志文件" + logFileName);
            log = new PrintWriter(System.err);
        }
    }

    public synchronized static Logger getInstance(Class<?> c) {
        if (logger == null) {
            logger = new Logger(c);
        }
        return logger;
    }


    public void debug(String msg) {
        if (level > DEBUGLEVEL) {
            msg = "DEBUG:" + new Date() + "-" + msg;
            System.out.println(msg);
            log.println(msg);
        }

    }

    public void info(String msg) {
        if (level > INFOLEVEL) {
            msg = "INFO:" + new Date() + "-" + msg;
            System.out.println(msg);
            log.println(msg);
        }

    }

    public void error(String msg) {
        if (level > ERRORLEVEL) {
            msg = "ERROR:" + new Date() + "-" + c + "-" + msg;
            System.out.println(msg);
            log.println(msg);
        }
    }
}
