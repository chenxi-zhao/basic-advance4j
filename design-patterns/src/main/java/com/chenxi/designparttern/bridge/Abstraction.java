package com.chenxi.designparttern.bridge;

/**
 * Created by chenxi on 2017/8/10.
 *
 * @author chenxi
 */
public abstract class Abstraction {

    protected Implementor impl; //定义实现类接口对象

    public void setImpl(Implementor impl) {
        this.impl = impl;
    }

    public abstract void operation();  //声明抽象业务方法
}
