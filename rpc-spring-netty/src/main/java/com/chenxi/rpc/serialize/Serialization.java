package com.chenxi.rpc.serialize;

/**
 * Created by chenxi on 2017/8/15.
 *
 * @author chenxi
 */
public interface Serialization {
    <T> byte[] serialize(T obj);

    <T> T deserialize(byte[] data, Class<T> cls);
}
