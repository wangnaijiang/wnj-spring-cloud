package com.wnj.invoker;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    static final AtomicInteger POOL_NUM = new AtomicInteger(1);
    private final AtomicInteger threadNum = new AtomicInteger(1);
    private final ThreadGroup threadGroup;
    private final String namePrefix;
    private final boolean daemon;

    public NamedThreadFactory(){this("pool");}
    public NamedThreadFactory(String name){this("pool",false);}
    public NamedThreadFactory(String preFix,boolean daemon){
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = preFix + "-" + POOL_NUM.getAndIncrement() + "-thread-";
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r, namePrefix + threadNum.getAndIncrement(), 0);
        thread.setDaemon(daemon);
        if(thread.getPriority() != Thread.NORM_PRIORITY){
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
