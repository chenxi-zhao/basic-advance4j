package com.chenxi.designparttern.bridge;

/**
 * Created by chenxi on 2017/8/10.
 *
 * @author chenxi
 */
public class RefinedAbstraction extends Abstraction {
    @Override
    public void operation() {
        //业务代码
        impl.operationImpl();  //调用实现类的方法
        //业务代码
    }
}
