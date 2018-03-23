package com.chenxi.javacore.java8;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * Created by chenxi on 2017/8/13.
 *
 * @author chenxi
 */
public class OthersNewDemo {
    // 异常升级
    public static void tryWithResources() throws IOException {
        // 在JDK7种提出了try-with-resources机制， 它规定你操作的类只要是实现了AutoCloseable接口
        // 就可以在try语句块退出的时候自动调用close 方法关闭流资源。
        try (InputStream ins = new FileInputStream("/home/biezhi/a.txt")) {
            char charStr = (char) ins.read();
            System.out.print(charStr);
        }

        // 关闭多个资源
        try (InputStream is = new FileInputStream("/home/biezhi/a.txt");
             OutputStream os = new FileOutputStream("/home/biezhi/b.txt")
        ) {
            char charStr = (char) is.read();
            os.write(charStr);
        }

        // 捕获多个异常
        try {
            Thread.sleep(20000);
            FileInputStream fis = new FileInputStream("/a/b.txt");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        // JDK7修复了这个缺陷，引入了一个新类ReflectiveOperationException可以帮你捕获这些反射异常
        // IllegalAccessException/InvocationTargetException/ClassNotFoundException
        try {
            Class<?> clazz = Class.forName("com.biezhi.apple.User");
            // clazz.getMethods()[0].invoke(object);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public static void fileOperation() throws IOException {
        // 要创建一个Path对象有多种方法，首先是final类Paths的两个static方法，如何从一个路径字符串来构造Path对象：
        Path path1 = Paths.get("/home/biezhi", "a.txt");
        Path path2 = Paths.get("/home/biezhi/a.txt");
        URI u = URI.create("file:////home/biezhi/a.txt");
        Path pathURI = Paths.get(u);
        // 通过FileSystems构造
        Path filePath = FileSystems.getDefault().getPath("/home/biezhi", "a.txt");
        // Path、URI、File之间的转换
        File file = new File("/home/biezhi/a.txt");
        Path p1 = file.toPath();
        p1.toFile();
        file.toURI();


        // 读写文件
        byte[] data = Files.readAllBytes(Paths.get("/home/biezhi/a.txt"));
        String content = new String(data, StandardCharsets.UTF_8);

        List<String> lines = Files.readAllLines(Paths.get("/home/biezhi/a.txt"));
        // 反之你想将字符串写入到文件可以调用
        Files.write(Paths.get("/home/biezhi/b.txt"), "Hello JDK7!".getBytes());
        // 你也可以按照行写入文件，Files.write方法的参数中支持传递一个实现Iterable接口的类实例。 将内容追加到指定文件可以使用write方法的第三个参数APPENDOption:
        Files.write(Paths.get("/home/biezhi/b.txt"), "Hello JDK7!".getBytes(), StandardOpenOption.APPEND);


    }


}
