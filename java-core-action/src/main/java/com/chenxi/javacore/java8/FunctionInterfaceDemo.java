package com.chenxi.javacore.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by chenxi on 2017/8/13.
 *
 * @author chenxi
 */
public class FunctionInterfaceDemo {

    @FunctionalInterface
    interface Predicate<T> {
        boolean test(T t);
    }

    public static boolean doPredicate(int age, Predicate<Integer> predicate) {
        return predicate.test(age);
    }

    /**
     * 消费型接口
     *
     * @param money
     * @param consumer
     */
    public static void donation(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    /**
     * 供给型接口
     *
     * @param num
     * @param supplier
     * @return
     */
    public static List<Integer> supply(Integer num, Supplier<Integer> supplier) {
        List<Integer> resultList = new ArrayList<>();
        for (int x = 0; x < num; x++)
            resultList.add(supplier.get());
        return resultList;
    }

    /**
     * 函数式接口
     *
     * @param str
     * @param function
     * @return
     */
    public static Integer convert(String str, Function<String, Integer> function) {
        return function.apply(str);
    }


    public static void main(String[] args) {
        boolean isAdult = doPredicate(20, x -> (x >= 18));
        System.out.println(isAdult);

        //消费型
        donation(1000, money -> System.out.println("好心的麦乐迪为Blade捐赠了" + money + "元"));

        // 供给型
        List<Integer> list = supply(10, () -> (int) (Math.random() * 100));
        list.forEach(System.out::print);
        System.out.println();

        // 函数型
        Integer value = convert("28", Integer::parseInt);
        //Integer value = convert("28", x -> Integer.parseInt(x);

        list = Arrays.asList(2, 7, 3, 1, 8, 6, 4);
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
    }
}
