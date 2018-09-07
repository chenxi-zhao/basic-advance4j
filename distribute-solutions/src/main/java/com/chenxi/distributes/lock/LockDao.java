package com.chenxi.distributes.lock;

import java.util.concurrent.TimeUnit;

public interface LockDao {
    boolean acquireLocker(String key, String token, TimeUnit unit, long duration);

    String getLockerOwner(String key);

    void releaseLocker(String key);
}
