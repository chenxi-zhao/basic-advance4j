package com.chenxi.servletcontainer.container;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenxi on 2017/3/26.
 *
 * @author chenxi
 */
public class HttpConnector implements Runnable {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    public static final String SHUT_DOWN_CMD = "/shutdown";

    private int port = 9099;

    private boolean shutdown = false;

    private String scheme = "http";

    public int getPort() {
        return port;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public String getScheme() {
        return scheme;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port, 5, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        while (!shutdown) {
            try {
                socket = serverSocket.accept();

                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}
