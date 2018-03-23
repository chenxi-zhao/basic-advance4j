package com.chenxi.springiocaop.ioc;

/**
 * Created by zhaochx1 on 2017/2/17.
 *
 * @author zhaochx1
 */
public class UserService {
    private UserDao userDao;

    public void addUser(User u) {
        this.userDao.save(u);
    }

    /**
     * @return the userDAO
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDAO to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
