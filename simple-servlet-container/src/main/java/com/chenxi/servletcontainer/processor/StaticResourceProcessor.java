package com.chenxi.servletcontainer.processor;

import com.chenxi.servletcontainer.http.HttpRequest;
import com.chenxi.servletcontainer.http.HttpResponse;

import java.io.IOException;

/**
 * Created by chenxi on 2017/3/26.
 *
 * @author chenxi
 */
public class StaticResourceProcessor {
    private HttpRequest request;
    private HttpResponse response ;

    public StaticResourceProcessor(HttpRequest request, HttpResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * 处理静态资源
     */
    public void process(){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
