package com.chenxi.servletcontainer.container;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chenxi on 2017/3/26.
 *
 * @author chenxi
 */
public class SocketInputStream extends ServletInputStream {
    private InputStream inputStream = null;

    public SocketInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        if(inputStream!=null){
            return inputStream.read();
        }
        return -1;

    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }
}
