package com.chenxi.distributes.lock;

import sun.management.VMManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DistributeLock implements Lock {
    private TimeUnit overTimeUnit;

    private long defaultLockTime;

    private LockDao lockDao;


    @Override
    public boolean acquireLocker(String key, boolean failBack) throws Exception {
        return acquireLocker(key, getUniqueToken(), failBack);
    }

    private boolean acquireLocker(String key, String token, boolean failBack) throws Exception {
        long startTime = new Date().getTime();
        boolean isGetLocker = false;
        while (true) {
            // 获取锁，锁得过期时间为默认一分钟，超过一分钟直接释放锁
            if (lockDao.acquireLocker(key, token, overTimeUnit, defaultLockTime)) {
                isGetLocker = true;
                break;
            }
            // 获取锁token
            String perToken = lockDao.getLockerOwner(key);
            // 如果获取的时候刚好被释放，则继续获取（说明不是本线程获取的）
            if (perToken == null) {
                continue;
            }
            // token与缓存中一样说明该锁当前线程持有，已经拿到锁,则直接返回
            if (perToken.equals(token)) {
                isGetLocker = true;
                break;
            }

            //如果需要没有拿到锁得时候直接返回
            if (failBack) {
                break;
            }

            // 100ms获取一次锁，1分钟过期
            Thread.sleep(100);
            if ((new Date().getTime() - startTime) / 1000 > 60) {
                throw new Exception("保存或删除超时： 获取榜单锁失败！");
            }
        }
        return isGetLocker;
    }

    @Override
    public void releaseLocker(String key) throws Exception {
        lockDao.releaseLocker(key);
    }

    /**
     * 获取机器的唯一码 Mac地址 + jvmPid + threadId
     */
    @Override
    public String getUniqueToken() throws Exception {
        return getLocalMac() + "-" + jvmPid() + "-" + Thread.currentThread().getId();
    }


    /**
     * 获取机器分布式环境下得唯一标识
     */
    private String localMac;

    private Integer jvmPid;

    private Integer jvmPid() throws Exception {
        try {
            if (this.jvmPid == null) {
                RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
                Field jvm = runtime.getClass().getDeclaredField("jvm");
                jvm.setAccessible(true);
                VMManagement mgmt = (VMManagement) jvm.get(runtime);
                Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
                pidMethod.setAccessible(true);
                this.jvmPid = (Integer) pidMethod.invoke(mgmt);
            }
            return this.jvmPid;
        } catch (Exception e) {
            throw new Exception("加锁失败： 获取锁键对象失败 jvmId");
        }
    }

    private String getLocalMac() throws Exception {
        try {
            if (this.localMac == null) {
                //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
                byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();

                //下面代码是把mac地址拼装成String
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append("-");
                    }
                    //mac[i] & 0xFF 是为了把byte转化为正整数
                    String s = Integer.toHexString(mac[i] & 0xFF);

                    sb.append(s.length() == 1 ? 0 + s : s);
                }

                //把字符串所有小写字母改为大写成为正规的mac地址并返回
                this.localMac = sb.toString().toUpperCase().replace("-", "");
            }

            return this.localMac;
        } catch (Exception e) {
            throw new Exception("加锁失败： 获取锁键对象失败 Mac地址");
        }
    }
}
