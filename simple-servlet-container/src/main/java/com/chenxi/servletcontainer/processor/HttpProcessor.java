package com.chenxi.servletcontainer.processor;

import com.chenxi.servletcontainer.container.HttpConnector;
import com.chenxi.servletcontainer.container.SocketInputStream;
import com.chenxi.servletcontainer.http.HttpRequest;
import com.chenxi.servletcontainer.http.HttpResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by chenxi on 2017/3/26.
 *
 * @author chenxi
 */
public class HttpProcessor {
    private HttpConnector httpConnector;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {
        try {
            byte[] buffer = new byte[2048];
            SocketInputStream socketInputStream = new SocketInputStream(socket.getInputStream());
            this.httpRequest = new HttpRequest();
            this.httpResponse = new HttpResponse(socket.getOutputStream());
            this.httpResponse.setRequest(this.httpRequest);


            int i = socketInputStream.readLine(buffer, 0, buffer.length);
            if (i != -1) {
                parseRequest(buffer, i);//处理请求行:GET /form.html HTTP/1.1
            }
            //处理请求头
            // parseHeaders(input,buff);
            if (this.httpRequest.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor(this.httpRequest, this.httpResponse);
                processor.process();
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor(this.httpRequest, this.httpResponse);
                processor.process();
            }
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理请求头
     *
     * @param inputStream
     */
    private void parseHeaders(SocketInputStream inputStream, byte[] buff) {

        try {
            int i = inputStream.readLine(buff, 0, buff.length);
            for (; i != -1; i = inputStream.readLine(buff, 0, buff.length)) {
                StringBuffer sb = new StringBuffer(i);
                for (int j = 0; j < i; j++) {
                    sb.append((char) buff[j]);
                }
                String headerString = sb.toString();
                int index = headerString.indexOf(":");
                if (index != -1) {
                    String key = headerString.substring(0, index);
                    String value = headerString.substring(index + 2, headerString.length() - 2);
                    this.httpRequest.addHeader(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理请求行
     *
     * @param buff
     * @param len
     */
    private void parseRequest(byte[] buff, int len) {
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            sb.append((char) buff[i]);
        }
        int index = sb.indexOf(" ");
        String method = sb.substring(0, index);
        String uri = "";
        int index1 = sb.indexOf("/", index);
        int index2 = -1;
        if (index1 != -1) {
            index2 = sb.indexOf(" ", index1);
        }
        if (index1 < index2) {
            uri = sb.substring(index1, index2);
        }
        //检查URI是否带HTTP协议前缀
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            if (pos != -1) {
                pos = uri.indexOf("/", pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }

            }
        }
        int question = sb.indexOf("?");
        String queryString = "";
        if (question != -1) {
            queryString = sb.substring(question + 1, index2);//请求参数
        }
        this.httpRequest.setQueryString(queryString);
        this.httpRequest.setRequestedUri(uri);
        this.httpRequest.setMethod(method);
    }
}
