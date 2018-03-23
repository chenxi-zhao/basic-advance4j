package com.chenxi.rpc.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chenxi on 2017/8/15.
 *
 * @author chenxi
 */
public class RpcBootstrap {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-server.xml");
    }
}

