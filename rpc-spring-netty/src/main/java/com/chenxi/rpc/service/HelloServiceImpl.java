package com.chenxi.rpc.service;

import com.chenxi.rpc.server.anotation.RpcService;

/**
 * Created by chenxi on 2017/6/6.
 *
 * @author chenxi
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello() {
        System.out.println("Hello World");
        return "Hello World!";
    }
}
