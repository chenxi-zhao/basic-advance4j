package com.chenxi.rpc.registry;

/**
 * Created by chenxi on 2017/8/15.
 *
 * @author chenxi
 */
public interface Constant {

    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}
