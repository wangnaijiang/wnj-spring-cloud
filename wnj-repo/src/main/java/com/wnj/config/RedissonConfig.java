package com.wnj.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class RedissonConfig {

    @Autowired
    private RedissonNacosConfig redissonNacosConfig;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.setCodec(new StringCodec(StandardCharsets.UTF_8));
        config.useSingleServer()
                .setAddress(redissonNacosConfig.getAddress())
                .setPassword(redissonNacosConfig.getPassword())
                .setDatabase(redissonNacosConfig.getDatabase())
                .setConnectionMinimumIdleSize(redissonNacosConfig.getConnectionMinimumIdleSize())
                .setConnectionPoolSize(redissonNacosConfig.getConnectionPoolSize())
                .setIdleConnectionTimeout(redissonNacosConfig.getIdleConnectionTimeout())
                .setConnectTimeout(redissonNacosConfig.getConnectTimeout())
                .setTimeout(redissonNacosConfig.getTimeout());
        return Redisson.create(config);
    }

}
