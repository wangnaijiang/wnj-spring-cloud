package com.wnj.javabase;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; //yyyy-MM-dd HH:mm:ss日期格式字符串
    public static String YYYY_MM = "yyyy-MM"; //yyyy-MM日期格式字符串
    public static String YYYY_MM_dd = "yyyy-MM-dd"; //yyyy-MM-dd日期格式字符串
    public static String YYYY_MM_DD_HH_MM_SS_V2 = "yyMMddHHmmsss"; //yyMMddHHmmsss日期格式字符串


    @Test
    public void test(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        LocalDateTime parse = LocalDateTime.parse("2021-01-31 10:00:00", dateTimeFormatter);
        LocalDateTime time2 = LocalDateTime.parse("2021-02-28 10:00:00", dateTimeFormatter);
        LocalDateTime time = parse.plusMonths(1);
        String format = time.format(dateTimeFormatter);
        System.out.println(format);
        System.out.println(time.isBefore(parse));
        System.out.println(time.isAfter(parse));
        System.out.println(time.isBefore(time2));
        System.out.println(time.isAfter(time2));

        LocalDateTime time23 = time.withHour(23).withMinute(59).withSecond(59);
        System.out.println(time);
        System.out.println(time23);

        System.out.println("=================毫秒数===========");
        LocalDateTime now = LocalDateTime.now();
        long currentTimeMillis = System.currentTimeMillis();

        System.out.println(now.format(dateTimeFormatter));  //2024-04-12 10:15:31
        System.out.println(currentTimeMillis);              //当地时间(北京东八区) 1712888131125
        //方式一
        long nowTime = Timestamp.valueOf(now).getTime();
        System.out.println(nowTime);                        //当地时间(北京东八区) 1712888131125

        //方式二
        long time8 = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(time8);                          //当地时间(北京东八区) 1712888131125

        long timeUtc = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        System.out.println(timeUtc);                        //UTC时间(0区) 1712916931125, 比东八区的大

        long time0 = now.toInstant(ZoneOffset.of("+0")).toEpochMilli();
        System.out.println(time0);                          //UTC时间(0区) 1712916931125, 比东八区的大


        System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));    //2024-04-12T11:22:49.286
        System.out.println(now.format(DateTimeFormatter.ISO_TIME));         //11:22:49.286
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));  //2024-04-12T11:22:49.286
        System.out.println(now.format(DateTimeFormatter.ISO_ORDINAL_DATE));     //2024-103
    }

}
