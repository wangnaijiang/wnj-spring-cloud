package com.wnj.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author WangNaiJiang
 * @createDate 2024-04-10 09:06:45
 */
public class HuToolDateUtilTest {
    @Test
    public void testDate(){
        Date s = DateUtil.parse("2024-01-31 12:12:12");
        Date e = DateUtil.parse("2024-04-21 13:12:12");

        System.out.println(DateUtil.betweenMonth(s,e,true));
        System.out.println(DateUtil.betweenMonth(e,s,true));
        for (int i = 0; i < 50; i++) {
            test(s, i);
        }

    }

    private void test(Date s, int month){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(s.getTime());
        cal.add(Calendar.MONTH, month);
        System.out.println(DateUtil.format(cal.getTime(), DatePattern.NORM_DATETIME_PATTERN));
    }
}
