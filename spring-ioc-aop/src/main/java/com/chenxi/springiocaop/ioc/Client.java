package com.chenxi.springiocaop.ioc;

/**
 * Created by zhaochx1 on 2017/2/17.
 *
 * @author zhaochx1
 */
public class Client {
    public static void main(String[] args) throws Exception {
        BeanFactory factory = new ClassPathXmlApplicationContext();
        //通过工厂直接获取
        UserService userService = (UserService) factory.getBean("userService");
        //其实User也可以从工厂中获得
        User u=(User)factory.getBean("user");
        //User u = new User();
        u.setUserName("yyb");
        u.setPassword("1234");
        userService.addUser(u);//打印结果yyb1234
    }
}
