package com.chenxi.servletcontainer.processor;

import com.chenxi.servletcontainer.http.HttpRequest;
import com.chenxi.servletcontainer.container.HttpConnector;
import com.chenxi.servletcontainer.http.HttpResponse;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by chenxi on 2017/3/26.
 *
 * @author chenxi
 */
public class ServletProcessor {
    private HttpRequest request;
    private HttpResponse response;


    public ServletProcessor(HttpRequest request, HttpResponse response) {
        this.request = request;
        this.response= response;
    }

    public void process(){
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader = null;
        try{
            URL[] urls = new URL[1];
            URLStreamHandler handler = null;
            File classPath = new File(HttpConnector.WEB_ROOT);
            String respository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
            urls[0] = new URL(null,respository,handler);
            loader = new URLClassLoader(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class servletClass  = null;
        try {
            servletClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            try {
                response.getWriter().println("<h1>Servlet Not Exist</h1>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        Servlet servlet = null;
        try {
            servlet = (Servlet) servletClass.newInstance();
            servlet.service(request,response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
