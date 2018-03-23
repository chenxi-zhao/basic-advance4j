package com.chenxi.javacore.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by chenxi on 2017/8/13.
 *
 * @author chenxi
 */
public class NewDateAPIDemo {
    public static void main(String[] args) {

    }

    public static void localDateTest() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate: " + localDate);

        // LocalDate可以指定特定的日期，调用of或parse方法返回该实例
        LocalDate.of(2017, 7, 20);
        LocalDate.parse("2017-07-20");

        // 为今天添加一天，也就是获取明天
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        // 从今天减去一个月
        LocalDate prevMonth = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        // 解析日期 2017-07-20，获取每周中的星期和每月中的日
        DayOfWeek thursday = LocalDate.parse("2017-07-20").getDayOfWeek();
        System.out.println("周四: " + thursday);
        int twenty = LocalDate.parse("2017-07-20").getDayOfMonth();
        System.out.println("twenty: " + twenty);

        // 是否闰年
        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println("是否闰年: " + leapYear);

        // 判断是否在日期之前或之后
        boolean notBefore = LocalDate.parse("2017-07-20").isBefore(LocalDate.parse("2017-07-22"));
        System.out.println("notBefore: " + notBefore);
        boolean isAfter = LocalDate.parse("2017-07-20").isAfter(LocalDate.parse("2017-07-22"));
        System.out.println("isAfter: " + isAfter);

        // 获取这个月的第一天
        LocalDate firstDayOfMonth = LocalDate.parse("2017-07-20").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("这个月的第一天: " + firstDayOfMonth);
        firstDayOfMonth = firstDayOfMonth.withDayOfMonth(1);
        System.out.println("这个月的第一天: " + firstDayOfMonth);

        // 判断今天是否是我的生日，例如我的生日是 2009-07-20
        LocalDate birthday = LocalDate.of(2009, 07, 20);
        MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
        MonthDay today = MonthDay.from(LocalDate.now());
        System.out.println("今天是否是我的生日: " + today.equals(birthdayMd));
    }

    public static void localTimeTest() {
        // 获取现在的时间
        LocalTime now = LocalTime.now();
        System.out.println("现在的时间: " + now);

        LocalTime nowTime = LocalTime.parse("15:02");
        System.out.println("时间是: " + nowTime);
        nowTime = LocalTime.of(15, 02);
        System.out.println("时间是: " + nowTime);

        // 使用解析字符串的方式并添加一小时，输出16:02
        LocalTime nextHour = LocalTime.parse("15:02").plus(1, ChronoUnit.HOURS);
        System.out.println("下一个小时: " + nextHour);

        // 获取时间的小时、分钟
        int hour = LocalTime.parse("15:02").getHour();
        System.out.println("小时: " + hour);
        int minute = LocalTime.parse("15:02").getMinute();
        System.out.println("分钟: " + minute);

        // 我们也可以通过之前类似的API检查一个时间是否在另一个时间之前、之后
        boolean isBefore = LocalTime.parse("15:02").isBefore(LocalTime.parse("16:02"));
        boolean isAfter = LocalTime.parse("15:02").isAfter(LocalTime.parse("16:02"));
        System.out.println("isBefore: " + isBefore);
        System.out.println("isAfter: " + isAfter);

        // 在LocalTime类中也将每天的开始和结束作为常量供我们使用:23:59:59.999999999 || 00:00
        System.out.println(LocalTime.MAX);
        System.out.println(LocalTime.MIN);


    }

    public static void localDateTimeTest() {
        // 现在: 2017-07-20T15:17:19.926
        LocalDateTime now = LocalDateTime.now();
        System.out.println("现在: " + now);

        // LocalDateTime.of(2017, Month.JULY, 20, 15, 18);
        LocalDateTime.parse("2017-07-20T15:18:00");

        // 同时`LocalDateTime`也提供了相关API来对日期和时间进行增减操作:
        LocalDateTime tomorrow = now.plusDays(1);
        System.out.println("明天的这个时间: " + tomorrow);
        LocalDateTime minusTowHour = now.minusHours(2);
        System.out.println("两小时前: " + minusTowHour);

        // 这个类也提供一系列的get方法来获取特定单位:
        Month month = now.getMonth();
        System.out.println("当前月份: " + month);

    }

    public static void formatterTest(){
        // 在日常开发中我们用到最多的也许就是日期、时间的格式化了
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("默认格式化: " + now);
        System.out.println("自定义格式化: " + now.format(dateTimeFormatter));
        LocalDateTime localDateTime = LocalDateTime.parse("2017-07-20 15:27:44", dateTimeFormatter);
        System.out.println("字符串转LocalDateTime: " + localDateTime);

        // 也可以使用DateTimeFormatter的format方法将日期、时间格式化为字符串
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = dateTimeFormatter.format(LocalDate.now());
        System.out.println("日期转字符串: " + dateString);
    }

}
