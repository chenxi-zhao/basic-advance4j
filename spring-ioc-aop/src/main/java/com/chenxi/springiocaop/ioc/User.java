package com.chenxi.springiocaop.ioc;

/**
 * Created by zhaochx1 on 2017/2/17.
 *
 * @author zhaochx1
 */
public class User {
    private String userName;
    private String password;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return this.userName + this.password;
    }
}
