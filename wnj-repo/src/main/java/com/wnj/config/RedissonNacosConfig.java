package com.wnj.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

//@RefreshScope
@Component
@Data
public class RedissonNacosConfig {

    @Value("${alone.redisson.address:redis://127.0.0.1:6379}")
    private String address;
    @Value("${alone.redisson.password:123456}")
    private String password;
    @Value("${alone.redisson.database:9}")
    private Integer database;
    @Value("${alone.redisson.connectionMinimumIdleSize:10}")
    private Integer connectionMinimumIdleSize;
    @Value("${alone.redisson.connectionPoolSize:10}")
    private Integer connectionPoolSize;
    @Value("${alone.redisson.idleConnectionTimeout:3600000}")
    private Integer idleConnectionTimeout;
    @Value("${alone.redisson.connectTimeout:5000}")
    private Integer connectTimeout;
    @Value("${alone.redisson.timeout:5000}")
    private Integer timeout;
}
