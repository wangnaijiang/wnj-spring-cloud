package com.wnj.juc;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {
    public static void main(String[] args) {
        // 创建一个AtomicReference，用于模拟不带版本号的CAS
        AtomicReference<Integer> atomicRef = new AtomicReference<>(100);
        
        // 创建一个AtomicStampedReference，用于模拟带版本号的CAS
        AtomicStampedReference<Integer> stampedRef = new AtomicStampedReference<>(100, 0);

        // 创建线程1，尝试进行CAS操作
        Thread thread1 = new Thread(() -> {
            int newValue = 101;
            
            // 使用AtomicReference进行CAS操作，更新值
            atomicRef.compareAndSet(100, newValue);
            
            // 使用AtomicStampedReference进行CAS操作，更新值并版本号加1
            stampedRef.compareAndSet(100, newValue, 0, 1);
            
            System.out.println("Thread 1: Value is updated to " + newValue);
        });

        // 创建线程2，模拟中间有其他线程修改过值
        Thread thread2 = new Thread(() -> {
            int newValue = 102;

            // 模拟中间有其他线程修改过值，使用AtomicReference将值设为99
            atomicRef.compareAndSet(100, 99);
            
            // 模拟中间有其他线程修改过值，使用AtomicStampedReference将值设为99，并版本号加1
            stampedRef.compareAndSet(100, 99, 0, 1);
            
            System.out.println("Thread 2: Value is updated to 99");

            // 再将值改回来，如果版本号匹配，CAS操作成功
            boolean success = atomicRef.compareAndSet(99, 100);
            boolean stampedSuccess = stampedRef.compareAndSet(99, 100, 1, 2);
            
            System.out.println("Thread 2: Value is updated back to 100: " + success);
            System.out.println("Thread 2 (Stamped): Value is updated back to 100: " + stampedSuccess);
        });

        // 启动线程1和线程2
        thread1.start();
        thread2.start();

        // 等待线程1和线程2执行完成
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出最终的值
        System.out.println("Final Value (AtomicReference): " + atomicRef.get());
        System.out.println("Final Value (AtomicStampedReference): " + stampedRef.getReference());
    }
}

