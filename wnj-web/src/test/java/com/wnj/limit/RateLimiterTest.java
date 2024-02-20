package com.wnj.limit;

import com.google.common.util.concurrent.RateLimiter;
import com.wnj.util.ThreadUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WangNaiJiang
 * @since 2024-01-16 16:03
 */
public class RateLimiterTest {
    public static void main(String[] args) {
        //SmoothRateLimiter, 添加令牌
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 10; i++) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
            System.out.println(time + ":" + rateLimiter.tryAcquire());
            ThreadUtil.sleep(250);
        }
    }
}
