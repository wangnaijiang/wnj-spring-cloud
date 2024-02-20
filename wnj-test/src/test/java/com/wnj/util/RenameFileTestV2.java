package com.wnj.util;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class RenameFileTestV2 {
    private static final String DIR = "D:/pic/2/";
    private static String tagOriginalName = "Date/Time Original";
    private static String tagModifiedName = "File Modified Date";


    public static void main(String[] args) throws Exception {
        RenameFileTestV2 t = new RenameFileTestV2();
        t.rename();
    }


    public void rename() throws Exception {
        File dir = new File(DIR);
        File[] files = dir.listFiles();
        for (File file : files) {
            String suffix = null;
            String fileName = file.getName();
            if(fileName.endsWith(".jpg") ||fileName.endsWith(".JPG") ){
                suffix = ".jpg";
//            }else if(fileName.endsWith(".jpg")){
//                suffix = ".mp4";
//            }else if(fileName.endsWith(".MOV")){
//                suffix = ".MOV";
            }else{
                continue;
            }
            String picTime = fetchPicFormatTime(file);
            if(StringUtil.isBlank(picTime)){
                continue;
            }
            String fullName = DIR + picTime + suffix;
            File destFile = new File(fullName);
            if(destFile.exists()){
                fullName = DIR + picTime + "_" + new Random().nextInt(1000) + suffix;
            }
            file.renameTo(new File(fullName));
        }
    }

    private static String fetchPicFormatTime(File jpegFile) {
        String originalTime = fetchPicOriginalTime(jpegFile);
        if(StringUtil.isNotBlank(originalTime)){
            Date date = DateUtil.parsePicTime(originalTime);
            if(date != null){
                return DateUtil.formatPicTime(date);
            }
        }
        return null;
    }


    private static String fetchPicOriginalTime(File jpegFile) {
        //优先取原始时间: 2020:12:30 10:00:22
        String originalTime = fetchDescription(jpegFile, tagOriginalName);
        if(StringUtil.isNotBlank(originalTime)){
            return originalTime;
        }
        //再取修改时间,星期三 十二月 30 10:00:22 +08:00 2020
        String modifiedTime = fetchDescription(jpegFile, tagModifiedName);
        if(StringUtil.isNotBlank(modifiedTime)){
            String[] arr = modifiedTime.split(" ");
            String monthNum = DateUtil.toMonthNum(arr[1]);
            return arr[5] + ":" + monthNum + ":" + arr[2] + " " + arr[3];
        }
        return null;
    }

    private static String fetchDescription(File jpegFile, String tagName) {
        try {
            Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    Tag tag = tags.next();
                    if(tag.getTagName().contains(tagName)){
                        return tag.getDescription();
                    }
//                    System.out.println(tag.getTagName() +"=="+tag.getDescription());
                }
            }
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}