package com.chenxi.springiocaop.ioc;

/**
 * Created by zhaochx1 on 2017/2/17.
 *
 * @author zhaochx1
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void save(User u) {
        System.out.println("User:" + u.toString());
    }

    @Override
    public void delete() {
        System.out.println("delete User");
    }
}
