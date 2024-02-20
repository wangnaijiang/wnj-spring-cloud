package com.wnj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * @author Administrator
 *
 */
public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat TIME_RESP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat TIGHT_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat PIC_TIME_SRC_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    public static final SimpleDateFormat PIC_TIME_DST_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static int SECONDS_OF_MINUTE = 60;
    public static int MINUTE_OF_HOUR = 60;
    public static int HOUR_OF_DAY = 24;
    public static int SECONDS_OF_HOUR = SECONDS_OF_MINUTE * MINUTE_OF_HOUR;
    public static int SECONDS_OF_DAY = SECONDS_OF_HOUR * HOUR_OF_DAY;


    /**
     * 判断一个时间是否在另一个时间之前
     * @param time1 第一个时间
     * @param time2 第二个时间
     * @return 判断结果
     */
    public static boolean before(String time1, String time2) {
        try {
            Date dateTime1 = TIME_FORMAT.parse(time1);
            Date dateTime2 = TIME_FORMAT.parse(time2);
            
            if(dateTime1.before(dateTime2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 判断一个时间是否在另一个时间之后
     * @param time1 第一个时间
     * @param time2 第二个时间
     * @return 判断结果
     */
    public static boolean after(String time1, String time2) {
        try {
            Date dateTime1 = TIME_FORMAT.parse(time1);
            Date dateTime2 = TIME_FORMAT.parse(time2);
            
            if(dateTime1.after(dateTime2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 计算时间差值（单位为秒）
     * @param time1 时间1
     * @param time2 时间2
     * @return 差值
     */
    public static int minus(String time1, String time2) {
        try {
            Date datetime1 = TIME_FORMAT.parse(time1);
            Date datetime2 = TIME_FORMAT.parse(time2);
            
            long millisecond = datetime1.getTime() - datetime2.getTime();
            
            return Integer.valueOf(String.valueOf(millisecond / 1000));  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * 获取年月日和小时
     * @param datetime 时间（yyyy-MM-dd HH:mm:ss）
     * @return 结果
     */
    public static String getDateHour(String datetime) {
        String date = datetime.split(" ")[0];
        String hourMinuteSecond = datetime.split(" ")[1];
        String hour = hourMinuteSecond.split(":")[0];
        return date + "_" + hour;
    }  
    
    /**
     * 获取当天日期（yyyy-MM-dd）
     * @return 当天日期
     */
    public static String getTodayDate() {
        return DATE_FORMAT.format(new Date());  
    }
    
    /**
     * 获取昨天的日期（yyyy-MM-dd）
     * @return 昨天的日期
     */
    public static String getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());  
        cal.add(Calendar.DAY_OF_YEAR, -1);  
        
        Date date = cal.getTime();
        
        return DATE_FORMAT.format(date);
    }
    
    /**
     * 格式化日期（yyyy-MM-dd）
     * @param date Date对象
     * @return 格式化后的日期
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }
    
    /**
     * 格式化时间（yyyy-MM-dd HH:mm:ss）
     * @param date Date对象
     * @return 格式化后的时间
     */
    public static String formatTime(Date date) {
        String format = TIME_FORMAT.format(date);
//        LoggerUtil.info(logger,"param={0},result={1}",date,format);
        return format;
    }

    /**
     * 格式化时间（yyyy-MM-dd HH:mm）
     * @param date Date对象
     * @return 格式化后的时间
     */
    public static String formatRespTime(Date date) {
        String format = TIME_RESP_FORMAT.format(date);
        LoggerUtil.info(logger,"param={0},result={1}",date,format);
        return format;
    }

    /**
     * 解析时间
     */
    public static Date parsePicTime(String dateStr) {
        try{
            return PIC_TIME_SRC_FORMAT.parse(dateStr);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 格式化时间（yyyy-MM-dd_HHmmss）
     * @param date Date对象
     * @return 格式化后的时间
     */
    public static String formatPicTime(Date date) {
        return PIC_TIME_DST_FORMAT.format(date);
    }


    /**
     * 格式化时间（yyyyMMddHHmmss）
     * @param date Date对象
     * @return 格式化后的时间
     */
    public static String formatTightTime(Date date) {
        return TIGHT_TIME_FORMAT.format(date);
    }

    public static String toMonthNum(String monthName){
        if("一月".equals(monthName)){
            return "01";
        }else if("二月".equals(monthName)){
            return "02";
        }else if("三月".equals(monthName)){
            return "03";
        }else if("四月".equals(monthName)){
            return "04";
        }else if("五月".equals(monthName)){
            return "05";
        }else if("六月".equals(monthName)){
            return "06";
        }else if("七月".equals(monthName)){
            return "07";
        }else if("八月".equals(monthName)){
            return "08";
        }else if("九月".equals(monthName)){
            return "09";
        }else if("十月".equals(monthName)){
            return "10";
        }else if("十一月".equals(monthName)){
            return "11";
        }else if("十二月".equals(monthName)){
            return "12";
        }
        throw new RuntimeException("不支持的月份"+monthName);
    }
}