package com.wnj.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Component
public class RedissonLockService {

    @Autowired
    private RedissonClient redissonClient;

    public void tryLock(String key, Runnable runnable) {
        RLock lock = redissonClient.getLock(key);
        try {
            if (!lock.tryLock(3L, 30, TimeUnit.SECONDS)) {
                log.warn("被锁定, key:{}", key);
                throw new RuntimeException("操作失败, 请重试");
            }
        } catch (InterruptedException e) {
            log.warn("锁失败, ", e);
            throw new RuntimeException("操作失败, 请重试");
        }
        try {
            runnable.run();
        }finally {
            lock.forceUnlock();
        }

    }

    public <T> T tryLock(String key, Supplier<T> supplier) {
        RLock lock = redissonClient.getLock(key);
        try {
//            if (!lock.tryLock(3L, 30, TimeUnit.SECONDS)) {
            if (!lock.tryLock(3L, 0, TimeUnit.SECONDS)) {
                log.warn("被锁定, key:{}", key);
                throw new RuntimeException("操作失败, 请重试");
            }
        } catch (InterruptedException e) {
            log.warn("锁失败, ", e);
            throw new RuntimeException("操作失败, 请重试");
        }
        try {
            return supplier.get();
        }finally {
//            lock.unlockAsync();
            lock.forceUnlock();
        }
    }

}
