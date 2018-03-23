package com.chenxi.javacore.java8;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by chenxi on 2017/8/13.
 *
 * @author chenxi
 */
public class OptionalDemo {

    static class User {
        String name;
        public String getName() {
            return name;
        }
    }
    public static String getUserName(User user){
        if(user != null){
            return user.getName();
        }
        return null;
    }

    public static String getUserNameByOptional(User user) {
        // 当user为null的时候我们设置UserName的值为null，否则返回getName的返回值，但此时不会抛出空指针。
        Optional<String> userName = Optional.ofNullable(user).map(User::getName);
        return userName.orElse(null);

    }

    public static void main(String[] args) {
        // Objects类添加了两个静态方法isNull和nonNull，在使用流的时候非常有用
        Stream.of("a", "c", null, "d").filter(Objects::nonNull).forEach(System.out::println);

        User user = new User();
        //你可以通过静态工厂方法Optional.empty，创建一个空的Optional对象：
        Optional<User> emptyUser = Optional.empty();
        // 创建一个非空值的Optional
        Optional<User> userOptional = Optional.of(user);
        // 可接受null的Optional
        Optional<User> ofNullOptional = Optional.ofNullable(user);



    }
}
