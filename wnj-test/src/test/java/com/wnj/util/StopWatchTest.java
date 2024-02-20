package com.wnj.util;

import org.springframework.util.StopWatch;

public class StopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        //创建一个StopWatch对象
        StopWatch stopWatch=new StopWatch();
        //开始计时
        stopWatch.start();
        //睡眠
        Thread.sleep(6);
        //结束计时
        stopWatch.stop();
        //打印耗时总时长
        System.out.println("耗时:"+stopWatch.getTotalTimeMillis()+"毫秒");
        System.out.println("耗时:"+stopWatch.getLastTaskTimeMillis()+"毫秒");

        //获取总耗时单位是秒
        System.out.println("总耗时:"+stopWatch.getTotalTimeSeconds()+"秒");
    }

}
