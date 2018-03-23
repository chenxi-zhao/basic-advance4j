package com.chenxi.rpc.client;

import com.chenxi.rpc.http.RpcRequest;
import com.chenxi.rpc.http.RpcResponse;
import com.chenxi.rpc.registry.ServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * Created by chenxi on 2017/8/15.
 *
 * @author chenxi
 */
public class RpcProxy {
    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;

    public RpcProxy(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);

                        if (serviceDiscovery != null) {
                            serverAddress = serviceDiscovery.discover(); // 发现服务
                        }

                        if (serverAddress != null) {
                            String[] array = serverAddress.split(":");
                            String host = array[0];
                            int port = Integer.parseInt(array[1]);

                            RpcClient client = new RpcClient(host, port); // 初始化 RPC 客户端
                            RpcResponse response = client.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

                            if (response.isError()) {
                                throw response.getError();
                            } else {
                                return response.getResult();
                            }
                        } else {
                            throw new RuntimeException("No server address found!");
                        }
                    }
                }
        );
    }
}
