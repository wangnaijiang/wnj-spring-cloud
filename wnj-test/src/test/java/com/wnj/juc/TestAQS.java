package com.wnj.juc;

import com.wnj.util.ThreadUtil;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author WangNaiJiang
 * @since 2024-01-29 22:17
 */
public class TestAQS {
    public static void main(String[] args) {
//        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock = new ReentrantLock(true);
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        System.out.println(lock.isFair());
        Thread t1 = new Thread(()->{
            lock.lock();
            System.out.println("t1.start");
            ThreadUtil.sleep(1000 * 5);
            System.out.println("t1.end");
            lock.unlock();
        });
        Thread t2 = new Thread(()->{
            ThreadUtil.sleep(1000 * 10);
            lock.lock();
            System.out.println("t2.start");
            ThreadUtil.sleep(1000 * 1000);
            System.out.println("t2.end");
            lock.unlock();
        });

        t1.start();
        t2.start();

        System.out.println("main.ends");
    }

}
