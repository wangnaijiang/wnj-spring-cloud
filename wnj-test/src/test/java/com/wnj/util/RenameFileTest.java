package com.wnj.util;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;

public class RenameFileTest {
    private final String DIR = "D:/pic/1/";

    @Test
    public void test() throws Exception {
        File dir = new File(DIR);
        File[] files = dir.listFiles();
        for (File file : files) {
            String name = file.getName();
            String finalName = null;
            if(name.startsWith("wx_camera_")){
                name = name.substring("wx_camera_".length());
            }
            if(name.startsWith("mmexport")){
                name = name.substring("mmexport".length());
            }
            if(name.matches("\\d{13}\\..{3}")){
                finalName = parseName4Long(name);
            }else if(name.startsWith("IMG_") || name.startsWith("VID_")){
                finalName = parseName4Phone(name);
            }
            if(finalName != null){
                String fullName = DIR + finalName;
                file.renameTo(new File(fullName));
                System.out.println(fullName);
            }
        }

    }

    private static String parseName4Phone(String name) {
        String finalName;
        String year = name.substring(4, 8);
        String month = name.substring(8, 10);
        String day = name.substring(10, 12);
        String nameTail = name.substring(12);
        finalName = year + "-" + month + "-" + day + nameTail;
        return finalName;
    }

    private static String parseName4Long(String name) {
        String finalName;
        String[] split = name.split("\\.");
        Date date = new Date(Long.valueOf(split[0]));
        String formatTime = DateUtil.formatPicTime(date);
        finalName = formatTime + "." + split[1];
        return finalName;
    }
}