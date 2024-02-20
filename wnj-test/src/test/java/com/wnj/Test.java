package com.wnj;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        long time = 3 * 1000;
        long current = System.currentTimeMillis();
        long threadId = Thread.currentThread().getId();
        Thread.sleep(100);

        time -= System.currentTimeMillis() - current;
        System.out.println(time);

        System.out.println(threadId);
    }
}
