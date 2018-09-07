package com.chenxi.distributes.lock;

public interface Lock {
    boolean acquireLocker(String key, boolean failBack) throws Exception;

    void releaseLocker(String key) throws Exception;

    String getUniqueToken() throws Exception;
}
