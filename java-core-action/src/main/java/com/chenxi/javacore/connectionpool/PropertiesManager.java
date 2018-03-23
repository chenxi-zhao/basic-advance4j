package com.chenxi.javacore.connectionpool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by chenxi on 2017/8/10.
 *
 * @author chenxi
 */
public class PropertiesManager {
    private static Properties pro = new Properties();

    private PropertiesManager() {
    }

    static {
        try {
            pro.load(new FileInputStream(new File("db.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return pro.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        //找不到key用defaultValue，而不是说后面为空字符串采用defaultValue
        return pro.getProperty(key, defaultValue);
    }

    public static Enumeration<?> propertiesNames() {
        return pro.propertyNames();
    }
}
