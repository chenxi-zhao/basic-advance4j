package com.chenxi.javacore.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chenxi on 2017/8/13.
 *
 * @author chenxi
 */

public class StreamDemo {

    public static void main(String[] args) {
        Property p1 = new Property("叫了个鸡", 1000, 500, 2);
        Property p2 = new Property("张三丰饺子馆", 2300, 1500, 3);
        Property p3 = new Property("永和大王", 580, 3000, 1);
        Property p4 = new Property("肯德基", 6000, 200, 4);
        List<Property> properties = Arrays.asList(p1, p2, p3, p4);
        Collections.sort(properties, (x, y) -> x.distance.compareTo(y.distance));
        String name = properties.get(0).name;
        System.out.println("距离我最近的店铺是:" + name);

        String name2 = properties.stream().sorted(Comparator.comparingInt(x -> x.distance)).findFirst().get().name;
        System.out.println("距离我最近的店铺是:" + name2);

        long count = properties.stream().filter(p -> p.sales > 1000).count();
        System.out.println("找出所有销量大于1000的店铺: " + count);

        // 筛选出价格等级小于4，按照距离排序的2个店铺名
        properties.stream().filter(property -> property.priceLevel < 4)
                .sorted(Comparator.comparingInt(Property::getDistance))
                .map(Property::getName).limit(2).collect(Collectors.toList());

        properties.parallelStream()
                .filter(p -> p.priceLevel < 4)
                .sorted(Comparator.comparingInt(Property::getDistance))
                .map(Property::getName)
                .limit(2)
                .collect(Collectors.toList());

    }

}

class Property {
    String name;
    // 距离，单位:米
    Integer distance;
    // 销量，月售
    Integer sales;
    // 价格，这里简单起见就写一个级别代表价格段
    Integer priceLevel;

    public Property(String name, int distance, int sales, int priceLevel) {
        this.name = name;
        this.distance = distance;
        this.sales = sales;
        this.priceLevel = priceLevel;
    }
    // getter setter 省略

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }
}
