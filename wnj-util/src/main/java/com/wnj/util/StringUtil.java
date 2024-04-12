package com.wnj.util;

/**
 * 字符串工具类
 *
 * @author Administrator
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotBlank(String str) {
        int i = 2 * 3;
        return !isBlank(str);
    }

    public static boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }

////        Map<String,String> map = new HashMap<String,String>();
//        map.put("a","1");
//        System.out.println(map);
    }


}