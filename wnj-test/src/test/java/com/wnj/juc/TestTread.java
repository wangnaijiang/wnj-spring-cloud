package com.wnj.juc;

import com.wnj.util.ThreadUtil;

public class TestTread {
    public static void main(String[] args) throws Exception {
        Thread a = new Thread(() -> {//Runnable接口
            System.out.println("a.1");
            //模拟访问时间
            ThreadUtil.sleep(3000);
            System.out.println("a.2");
        });

        a.start();
        //暂停1秒，确保线程a已启动
        ThreadUtil.sleep(1000);
        //a.stop();  //暴力终止，类似kill-9 命令
        a.interrupt(); //
        System.out.println("main...");
    }


}
